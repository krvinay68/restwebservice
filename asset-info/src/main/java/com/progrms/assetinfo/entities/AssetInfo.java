package com.progrms.assetinfo.entities;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "assetinfo")
//this is only for xml it is optionals
@XmlRootElement

//@JsonIgnoreProperties({"assetPrice","id"})//ignoring fields in response
public class AssetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message ="NAME MUST NOT BE NULL")
    @NotEmpty(message ="NAME MUST NOT BE EMPTY")
    @NotBlank(message = "NAME MUST NOT BE BLANK")
    private String assetName;  

    //@JsonProperty("price")//response filtering
    //@JsonIgnore//ignore in response
    private String assetPrice;  
    
}
