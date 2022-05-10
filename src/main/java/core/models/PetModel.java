package core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class PetModel {
/*
    {
      "id": 9223372036854013000,
      "category": {
        "id": 10,
        "name": "Rex"
      },
      "name": "doggie",
      "photoUrls": [
        "string"
      ],
      "tags": [
        {
          "id": 0,
          "name": "string"
        }
      ],
      "status": "available"
    }
 */
  @EqualsAndHashCode.Exclude public long id;
  public Category category;
  public String name;
  public ArrayList<String> photoUrls;
  public ArrayList<Tag> tags;
  public String status;

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class Tag{
    public Integer id;
    public String name;
   }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class Category{
    public Integer id;
    public String name;
  }

}
