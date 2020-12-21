package demohateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloController {

    private static final String TEMPLATE = "Hello, %s";

    @GetMapping("/hello")
    public HttpEntity<Hello> hello(
            @RequestParam(value = "name", defaultValue = "World", required = false) String name) {

        Hello hello = new Hello(String.format(TEMPLATE, name));
        hello.add(linkTo(methodOn(HelloController.class).hello(name)).withSelfRel());
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }

    @GetMapping("/hello2")
    public EntityModel<Hello> hello2() {
        return EntityModel.of(new Hello("hello world"), linkTo(methodOn(HelloController.class).hello2()).withSelfRel());
    }
}

/**
 * linkTo(), methodOn(): 컨트롤러에 가짜 메서드 콜을 생성 이를 통해 반환된 WebLinkBuilder는 컨트롤러 메서드의
 * 맵핑 어노테이션을 검사 메서드가 맵핑된 URI를 정확하게 빌드함 withSelfRel(): 대상(hello) 표현 모델의 Link
 * 인스턴스를 생성
 */