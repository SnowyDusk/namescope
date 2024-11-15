package namescope.fun.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import namescope.fun.web.enums.ResultEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameResponse<T> {

    private String code;

    private T data;

    public static <T> NameResponse<T> success(T data) {
        return new NameResponse<>(ResultEnum.SUCCESS.getCode(), data);
    }

    public static <T> NameResponse<T> success() {
        return new NameResponse<>(ResultEnum.SUCCESS.getCode(), null);
    }

}
