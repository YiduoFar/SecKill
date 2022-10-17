package com.ayi.seckill.vo;

import com.ayi.seckill.validator.IsMobile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author HuiBBao
 * @create 2022/10/2 11:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVo {

    @NotNull
    @IsMobile
    public String mobile;

    @NotNull
    @Length(min = 32)
    public String password;

}
