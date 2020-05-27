package person.jzh.hello.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/27 16:03
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String title;

    private String img;

    private String price;
}
