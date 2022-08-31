package za.co.standard.bank.pokemon.model.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ability",
    "berry",
    "berry-firmness",
    "berry-flavor",
    "characteristic",
    "contest-effect",
    "contest-type",
    "egg-group",
    "encounter-condition",
    "encounter-condition-value",
    "encounter-method",
    "evolution-chain",
    "evolution-trigger",
    "gender",
    "generation",
    "growth-rate",
    "item",
    "item-attribute",
    "item-category",
    "item-fling-effect",
    "item-pocket",
    "language",
    "location",
    "location-area",
    "machine",
    "move",
    "move-ailment",
    "move-battle-style",
    "move-category",
    "move-damage-class",
    "move-learn-method",
    "move-target",
    "nature",
    "pal-park-area",
    "pokeathlon-stat",
    "pokedex",
    "pokemon",
    "pokemon-color",
    "pokemon-form",
    "pokemon-habitat",
    "pokemon-shape",
    "pokemon-species",
    "region",
    "stat",
    "super-contest-effect",
    "type",
    "version",
    "version-group"
})
@Generated("jsonschema2pojo")
public class PokemonResources implements Serializable {

    @JsonProperty("ability")
    private String ability;
    @JsonProperty("berry")
    private String berry;
    @JsonProperty("berry-firmness")
    private String berryFirmness;
    @JsonProperty("berry-flavor")
    private String berryFlavor;
    @JsonProperty("characteristic")
    private String characteristic;
    @JsonProperty("contest-effect")
    private String contestEffect;
    @JsonProperty("contest-type")
    private String contestType;
    @JsonProperty("egg-group")
    private String eggGroup;
    @JsonProperty("encounter-condition")
    private String encounterCondition;
    @JsonProperty("encounter-condition-value")
    private String encounterConditionValue;
    @JsonProperty("encounter-method")
    private String encounterMethod;
    @JsonProperty("evolution-chain")
    private String evolutionChain;
    @JsonProperty("evolution-trigger")
    private String evolutionTrigger;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("generation")
    private String generation;
    @JsonProperty("growth-rate")
    private String growthRate;
    @JsonProperty("item")
    private String item;
    @JsonProperty("item-attribute")
    private String itemAttribute;
    @JsonProperty("item-category")
    private String itemCategory;
    @JsonProperty("item-fling-effect")
    private String itemFlingEffect;
    @JsonProperty("item-pocket")
    private String itemPocket;
    @JsonProperty("language")
    private String language;
    @JsonProperty("location")
    private String location;
    @JsonProperty("location-area")
    private String locationArea;
    @JsonProperty("machine")
    private String machine;
    @JsonProperty("move")
    private String move;
    @JsonProperty("move-ailment")
    private String moveAilment;
    @JsonProperty("move-battle-style")
    private String moveBattleStyle;
    @JsonProperty("move-category")
    private String moveCategory;
    @JsonProperty("move-damage-class")
    private String moveDamageClass;
    @JsonProperty("move-learn-method")
    private String moveLearnMethod;
    @JsonProperty("move-target")
    private String moveTarget;
    @JsonProperty("nature")
    private String nature;
    @JsonProperty("pal-park-area")
    private String palParkArea;
    @JsonProperty("pokeathlon-stat")
    private String pokeathlonStat;
    @JsonProperty("pokedex")
    private String pokedex;
    @JsonProperty("pokemon")
    private String pokemon;
    @JsonProperty("pokemon-color")
    private String pokemonColor;
    @JsonProperty("pokemon-form")
    private String pokemonForm;
    @JsonProperty("pokemon-habitat")
    private String pokemonHabitat;
    @JsonProperty("pokemon-shape")
    private String pokemonShape;
    @JsonProperty("pokemon-species")
    private String pokemonSpecies;
    @JsonProperty("region")
    private String region;
    @JsonProperty("stat")
    private String stat;
    @JsonProperty("super-contest-effect")
    private String superContestEffect;
    @JsonProperty("type")
    private String type;
    @JsonProperty("version")
    private String version;
    @JsonProperty("version-group")
    private String versionGroup;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ability")
    public String getAbility() {
        return ability;
    }

    @JsonProperty("ability")
    public void setAbility(String ability) {
        this.ability = ability;
    }

    @JsonProperty("berry")
    public String getBerry() {
        return berry;
    }

    @JsonProperty("berry")
    public void setBerry(String berry) {
        this.berry = berry;
    }

    @JsonProperty("berry-firmness")
    public String getBerryFirmness() {
        return berryFirmness;
    }

    @JsonProperty("berry-firmness")
    public void setBerryFirmness(String berryFirmness) {
        this.berryFirmness = berryFirmness;
    }

    @JsonProperty("berry-flavor")
    public String getBerryFlavor() {
        return berryFlavor;
    }

    @JsonProperty("berry-flavor")
    public void setBerryFlavor(String berryFlavor) {
        this.berryFlavor = berryFlavor;
    }

    @JsonProperty("characteristic")
    public String getCharacteristic() {
        return characteristic;
    }

    @JsonProperty("characteristic")
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    @JsonProperty("contest-effect")
    public String getContestEffect() {
        return contestEffect;
    }

    @JsonProperty("contest-effect")
    public void setContestEffect(String contestEffect) {
        this.contestEffect = contestEffect;
    }

    @JsonProperty("contest-type")
    public String getContestType() {
        return contestType;
    }

    @JsonProperty("contest-type")
    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    @JsonProperty("egg-group")
    public String getEggGroup() {
        return eggGroup;
    }

    @JsonProperty("egg-group")
    public void setEggGroup(String eggGroup) {
        this.eggGroup = eggGroup;
    }

    @JsonProperty("encounter-condition")
    public String getEncounterCondition() {
        return encounterCondition;
    }

    @JsonProperty("encounter-condition")
    public void setEncounterCondition(String encounterCondition) {
        this.encounterCondition = encounterCondition;
    }

    @JsonProperty("encounter-condition-value")
    public String getEncounterConditionValue() {
        return encounterConditionValue;
    }

    @JsonProperty("encounter-condition-value")
    public void setEncounterConditionValue(String encounterConditionValue) {
        this.encounterConditionValue = encounterConditionValue;
    }

    @JsonProperty("encounter-method")
    public String getEncounterMethod() {
        return encounterMethod;
    }

    @JsonProperty("encounter-method")
    public void setEncounterMethod(String encounterMethod) {
        this.encounterMethod = encounterMethod;
    }

    @JsonProperty("evolution-chain")
    public String getEvolutionChain() {
        return evolutionChain;
    }

    @JsonProperty("evolution-chain")
    public void setEvolutionChain(String evolutionChain) {
        this.evolutionChain = evolutionChain;
    }

    @JsonProperty("evolution-trigger")
    public String getEvolutionTrigger() {
        return evolutionTrigger;
    }

    @JsonProperty("evolution-trigger")
    public void setEvolutionTrigger(String evolutionTrigger) {
        this.evolutionTrigger = evolutionTrigger;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("generation")
    public String getGeneration() {
        return generation;
    }

    @JsonProperty("generation")
    public void setGeneration(String generation) {
        this.generation = generation;
    }

    @JsonProperty("growth-rate")
    public String getGrowthRate() {
        return growthRate;
    }

    @JsonProperty("growth-rate")
    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("item-attribute")
    public String getItemAttribute() {
        return itemAttribute;
    }

    @JsonProperty("item-attribute")
    public void setItemAttribute(String itemAttribute) {
        this.itemAttribute = itemAttribute;
    }

    @JsonProperty("item-category")
    public String getItemCategory() {
        return itemCategory;
    }

    @JsonProperty("item-category")
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @JsonProperty("item-fling-effect")
    public String getItemFlingEffect() {
        return itemFlingEffect;
    }

    @JsonProperty("item-fling-effect")
    public void setItemFlingEffect(String itemFlingEffect) {
        this.itemFlingEffect = itemFlingEffect;
    }

    @JsonProperty("item-pocket")
    public String getItemPocket() {
        return itemPocket;
    }

    @JsonProperty("item-pocket")
    public void setItemPocket(String itemPocket) {
        this.itemPocket = itemPocket;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("location-area")
    public String getLocationArea() {
        return locationArea;
    }

    @JsonProperty("location-area")
    public void setLocationArea(String locationArea) {
        this.locationArea = locationArea;
    }

    @JsonProperty("machine")
    public String getMachine() {
        return machine;
    }

    @JsonProperty("machine")
    public void setMachine(String machine) {
        this.machine = machine;
    }

    @JsonProperty("move")
    public String getMove() {
        return move;
    }

    @JsonProperty("move")
    public void setMove(String move) {
        this.move = move;
    }

    @JsonProperty("move-ailment")
    public String getMoveAilment() {
        return moveAilment;
    }

    @JsonProperty("move-ailment")
    public void setMoveAilment(String moveAilment) {
        this.moveAilment = moveAilment;
    }

    @JsonProperty("move-battle-style")
    public String getMoveBattleStyle() {
        return moveBattleStyle;
    }

    @JsonProperty("move-battle-style")
    public void setMoveBattleStyle(String moveBattleStyle) {
        this.moveBattleStyle = moveBattleStyle;
    }

    @JsonProperty("move-category")
    public String getMoveCategory() {
        return moveCategory;
    }

    @JsonProperty("move-category")
    public void setMoveCategory(String moveCategory) {
        this.moveCategory = moveCategory;
    }

    @JsonProperty("move-damage-class")
    public String getMoveDamageClass() {
        return moveDamageClass;
    }

    @JsonProperty("move-damage-class")
    public void setMoveDamageClass(String moveDamageClass) {
        this.moveDamageClass = moveDamageClass;
    }

    @JsonProperty("move-learn-method")
    public String getMoveLearnMethod() {
        return moveLearnMethod;
    }

    @JsonProperty("move-learn-method")
    public void setMoveLearnMethod(String moveLearnMethod) {
        this.moveLearnMethod = moveLearnMethod;
    }

    @JsonProperty("move-target")
    public String getMoveTarget() {
        return moveTarget;
    }

    @JsonProperty("move-target")
    public void setMoveTarget(String moveTarget) {
        this.moveTarget = moveTarget;
    }

    @JsonProperty("nature")
    public String getNature() {
        return nature;
    }

    @JsonProperty("nature")
    public void setNature(String nature) {
        this.nature = nature;
    }

    @JsonProperty("pal-park-area")
    public String getPalParkArea() {
        return palParkArea;
    }

    @JsonProperty("pal-park-area")
    public void setPalParkArea(String palParkArea) {
        this.palParkArea = palParkArea;
    }

    @JsonProperty("pokeathlon-stat")
    public String getPokeathlonStat() {
        return pokeathlonStat;
    }

    @JsonProperty("pokeathlon-stat")
    public void setPokeathlonStat(String pokeathlonStat) {
        this.pokeathlonStat = pokeathlonStat;
    }

    @JsonProperty("pokedex")
    public String getPokedex() {
        return pokedex;
    }

    @JsonProperty("pokedex")
    public void setPokedex(String pokedex) {
        this.pokedex = pokedex;
    }

    @JsonProperty("pokemon")
    public String getPokemon() {
        return pokemon;
    }

    @JsonProperty("pokemon")
    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    @JsonProperty("pokemon-color")
    public String getPokemonColor() {
        return pokemonColor;
    }

    @JsonProperty("pokemon-color")
    public void setPokemonColor(String pokemonColor) {
        this.pokemonColor = pokemonColor;
    }

    @JsonProperty("pokemon-form")
    public String getPokemonForm() {
        return pokemonForm;
    }

    @JsonProperty("pokemon-form")
    public void setPokemonForm(String pokemonForm) {
        this.pokemonForm = pokemonForm;
    }

    @JsonProperty("pokemon-habitat")
    public String getPokemonHabitat() {
        return pokemonHabitat;
    }

    @JsonProperty("pokemon-habitat")
    public void setPokemonHabitat(String pokemonHabitat) {
        this.pokemonHabitat = pokemonHabitat;
    }

    @JsonProperty("pokemon-shape")
    public String getPokemonShape() {
        return pokemonShape;
    }

    @JsonProperty("pokemon-shape")
    public void setPokemonShape(String pokemonShape) {
        this.pokemonShape = pokemonShape;
    }

    @JsonProperty("pokemon-species")
    public String getPokemonSpecies() {
        return pokemonSpecies;
    }

    @JsonProperty("pokemon-species")
    public void setPokemonSpecies(String pokemonSpecies) {
        this.pokemonSpecies = pokemonSpecies;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("stat")
    public String getStat() {
        return stat;
    }

    @JsonProperty("stat")
    public void setStat(String stat) {
        this.stat = stat;
    }

    @JsonProperty("super-contest-effect")
    public String getSuperContestEffect() {
        return superContestEffect;
    }

    @JsonProperty("super-contest-effect")
    public void setSuperContestEffect(String superContestEffect) {
        this.superContestEffect = superContestEffect;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("version-group")
    public String getVersionGroup() {
        return versionGroup;
    }

    @JsonProperty("version-group")
    public void setVersionGroup(String versionGroup) {
        this.versionGroup = versionGroup;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}