package top.erzhiqian.weixin.security.client.cmd;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ChangeBusinessStrategyCmd {

    @NotNull(message = "业务策略不能为空.")
    @Valid
    private List<BusinessStrategySetting> strategies;

}
