package person.jzh.rpc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jzh
 * @version 1.0.0
 * @title RPCTransformObj
 * @date 2019/12/20 16:19
 * @description：
 */
@Data
public class RPCTransformObj implements Serializable {

    private static final long serialVersionUID = 8721056664203046167L;

    private String fullClassPath;

    private String methodName;

    private Object[] params;
}
