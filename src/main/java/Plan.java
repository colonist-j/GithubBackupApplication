import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "name",
        "space",
        "collaborators",
        "private_repos"
})
public class Plan {

    @JsonProperty("name")
    private String name;
    @JsonProperty("space")
    private Integer space;
    @JsonProperty("collaborators")
    private Integer collaborators;
    @JsonProperty("private_repos")
    private Integer privateRepos;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The space
     */
    @JsonProperty("space")
    public Integer getSpace() {
        return space;
    }

    /**
     *
     * @param space
     * The space
     */
    @JsonProperty("space")
    public void setSpace(Integer space) {
        this.space = space;
    }

    /**
     *
     * @return
     * The collaborators
     */
    @JsonProperty("collaborators")
    public Integer getCollaborators() {
        return collaborators;
    }

    /**
     *
     * @param collaborators
     * The collaborators
     */
    @JsonProperty("collaborators")
    public void setCollaborators(Integer collaborators) {
        this.collaborators = collaborators;
    }

    /**
     *
     * @return
     * The privateRepos
     */
    @JsonProperty("private_repos")
    public Integer getPrivateRepos() {
        return privateRepos;
    }

    /**
     *
     * @param privateRepos
     * The private_repos
     */
    @JsonProperty("private_repos")
    public void setPrivateRepos(Integer privateRepos) {
        this.privateRepos = privateRepos;
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
