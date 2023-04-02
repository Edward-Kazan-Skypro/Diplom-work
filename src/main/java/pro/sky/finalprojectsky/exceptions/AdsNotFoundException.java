package pro.sky.finalprojectsky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class AdsNotFoundException extends RuntimeException {
}
