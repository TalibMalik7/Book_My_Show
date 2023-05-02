package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.Show;
import com.example.Book_My_Show.RequestDTO.ShowRequestDto;

public class ShowConvertor {

    public static Show showReqDto_show(ShowRequestDto showRequestDto){
        return Show.builder()
                .showDate(showRequestDto.getLocalDate())
                .showTime(showRequestDto.getLocalTime())
                .showType(showRequestDto.getShowType())
                .build();
    }

}
