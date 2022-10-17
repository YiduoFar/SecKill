package com.ayi.seckill.vo;

import com.ayi.seckill.validator.IsMobile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author HuiBBao
 * @create 2022/10/2 18:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegVo {
    @NotNull
    @IsMobile
    public String mobile;

    @NotNull
    @Length(min = 32)
    public String password;
}
