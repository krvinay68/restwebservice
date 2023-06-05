package com.progrms.assetinfo.exceptions;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorsDetails{

    private String errorName;
    private String errorDesc;
    private LocalDate timeStamp;
}
