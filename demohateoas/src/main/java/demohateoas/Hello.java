package demohateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;

@Getter
public class Hello extends RepresentationModel<Hello> {

    private final String content;

    @JsonCreator
    public Hello(@JsonProperty("content") String content) {
        this.content = content;
    }
}

/** Resource Representation class
 * @JsonCreator: Jackson이 POJO 인스턴스를 어떻게 만들지에 대한 신호
 * @JsonProperty: Jackson이 어떤 필드에 인자를 넣어야할지 확실하게 명시
 *                단일 인자에는 쓰지 않아도 됨(자동 맵핑)
 */