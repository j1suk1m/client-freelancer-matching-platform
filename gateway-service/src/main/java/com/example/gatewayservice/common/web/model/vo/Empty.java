<<<<<<< HEAD
package com.example.gatewayservice.common.web.model.vo;

=======
<<<<<<<< HEAD:member-service/src/main/java/com/example/memberservice/common/web/model/vo/Empty.java
package com.example.memberservice.common.web.model.vo;
========
package com.example.gatewayservice.common.web.model.vo;
>>>>>>>> 8c3a48354cbd620e0e2fcaa05a6cfdc794422e93:gateway-service/src/main/java/com/example/gatewayservice/common/web/model/vo/Empty.java

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = EmptySerializer.class)
>>>>>>> 8c3a48354cbd620e0e2fcaa05a6cfdc794422e93
public final class Empty {
    private static final Empty INSTANCE = new Empty();

    private Empty() {}

    public static Empty getInstance() {
        return INSTANCE;
    }
}