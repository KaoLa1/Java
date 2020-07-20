package com.muye.interview.lock;

import lombok.Getter;

/**
 * @author : gwh
 * @date : 2020-03-03 16:11
 **/
public enum CountryEnum {

    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "韩"), FIVE(5, "赵"), SIX(6, "魏");

    @Getter
    private Integer id;
    @Getter
    private String country;

    CountryEnum(Integer id, String country) {
        this.id = id;
        this.country = country;
    }

    public static CountryEnum forEach_Enum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if(index == value.getId()){
                return value;
            }
        }
        return null;
    }

}
