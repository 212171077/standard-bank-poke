package za.co.standard.bank.pokemon.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.standard.bank.pokemon.payload.ChangePassRequest;
import za.co.standard.bank.pokemon.payload.JwtResponse;
import za.co.standard.bank.pokemon.payload.MessageResponse;
import za.co.standard.bank.pokemon.payload.UserDetails;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends CommonTestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);
    private String baseURL;
    private String userUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        baseURL = "http://localhost:" + port;
        userUrl = baseURL + "/api/pokemon/user/v1";
    }

    @Test
    public void test_A_WhenCreatingUserProfileWithoutRoles_ThenReturn500() {
        String url = userUrl + "/signup";

        ResponseEntity<MessageResponse> responseEntity = this.restTemplate
            .postForEntity(url, getSignupRequest(), MessageResponse.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void test_B_WhenCreatingUserProfile_ThenReturn200() {
        String url = userUrl + "/signup";

        //Given
        saveRoles();

        ResponseEntity<MessageResponse> responseEntity = this.restTemplate
            .postForEntity(url, getSignupRequest(), MessageResponse.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test_C_WhenLoginWithValidLoginDetails_ThenReturn200() {
        String url = userUrl + "/signin";

        ResponseEntity<JwtResponse> responseEntity = this.restTemplate
            .postForEntity(url, getLoginRequest(), JwtResponse.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test_D_WhenLoginWithInvalidLoginDetails_ThenReturn401() {
        String url = userUrl + "/signin";

        ResponseEntity<JwtResponse> responseEntity = this.restTemplate
            .postForEntity(url, getInvalidLoginRequest(), JwtResponse.class);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void test_E_WhenFindingUserByID_ThenReturn200() {
        String url = userUrl + "/find_users/";

        ResponseEntity<JwtResponse> tokenResponse = this.restTemplate
            .postForEntity(userUrl + "/signin", getLoginRequest(), JwtResponse.class);

        assertEquals(HttpStatus.OK, tokenResponse.getStatusCode());

        String token = tokenResponse.getBody().getToken();
        url = url + "" + tokenResponse.getBody().getId();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        ResponseEntity<UserDetails> responseEntity = this.restTemplate.exchange(url,
            HttpMethod.GET, new HttpEntity<>(httpHeaders), UserDetails.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test_F_WhenUpdatingUser_ThenReturn200() {
        String url = userUrl + "/update_users/";

        ResponseEntity<JwtResponse> tokenResponse = this.restTemplate
            .postForEntity(userUrl + "/signin", getLoginRequest(), JwtResponse.class);

        assertEquals(HttpStatus.OK, tokenResponse.getStatusCode());

        String token = tokenResponse.getBody().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<UserDetails> request = new HttpEntity<>(getUserDetails(tokenResponse.getBody().getId()), headers);

        ResponseEntity<MessageResponse> responseEntity = this.restTemplate
            .postForEntity(url,request, MessageResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


    @Test
    public void test_G_WhenChangingPasswordPasswordWithInvalidOldPass_ThenReturn400() {
        String url = userUrl + "/change_password";

        ResponseEntity<JwtResponse> tokenResponse = this.restTemplate
            .postForEntity(userUrl + "/signin", getLoginRequest(), JwtResponse.class);

        assertEquals(HttpStatus.OK, tokenResponse.getStatusCode());

        String token = tokenResponse.getBody().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<ChangePassRequest> request = new HttpEntity<>(getChangePassRequest(tokenResponse.getBody().getId()), headers);

        LOGGER.info("CHANGE PASS URL: {}",url);
        ResponseEntity<MessageResponse> responseEntity = this.restTemplate
            .postForEntity(url,request, MessageResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }


}
