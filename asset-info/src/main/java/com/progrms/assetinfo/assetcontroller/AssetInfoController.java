package com.progrms.assetinfo.assetcontroller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.progrms.assetinfo.dao.AssetDao;
import com.progrms.assetinfo.entities.AssetInfo;

import jakarta.validation.Valid;

@RestController
public class AssetInfoController { 

    @Autowired
    AssetDao assetDao;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/about")
    public String testM(){
        String s1="IMPLEMENTATION JPA";
        String s2="IMPLEMENTATION HATEOAS(HYPERLINK)";
        String s3="IMPLEMENTATION OF LINK AND SETTING OF RESPONSE BODY IN POST CALL ";
        String s4="IMPLEMENTATION OF EXCEPTION";
        String s5="IMPLEMENTATION SWAGGER AND OPEN API";
        String s6="IMPLEMENTATION HAL";
        String s7="IMPLEMENTATION OF RESPONSE FILTERING";
        String s8="IMPLEMENTATION OF INTERNATIONALIZATION";
        String s9="IMPLEMENTATION OF VERSIONING";
        String s10="IMPLEMENTATION OF CONTENT NEGOTIATION NOTHING EITHER JSON RESPONSE OR XML";

        return s1+s2+s3+s4+s5+s6+s7+s8+s9+s10;
    }
    
    @PostMapping("/asset/create")    
    public ResponseEntity<AssetInfo> add(@Valid @RequestBody AssetInfo assetInfo){
        AssetInfo asset=assetDao.saveData(assetInfo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                               path("/{id}").buildAndExpand(asset.getId()).toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uri);
        return new ResponseEntity<AssetInfo>(asset, responseHeaders, HttpStatus.CREATED);
        //return ResponseEntity.created(uri).build();
      
    }
    @GetMapping(path="/asset/findall")
    public List<AssetInfo> findall(){
        List<AssetInfo> assetInfo=  assetDao.getAll();
        return assetInfo;
    }
    //Need to implement find by id logic
    @GetMapping(path="/asset/find")
    public EntityModel<AssetInfo> findOne(){        
        AssetInfo asetInfo=null;
        //HATEOAS property for creating hyperlink
        EntityModel<AssetInfo> entityModel=EntityModel.of(asetInfo);
        WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).findall());
        entityModel.add(link.withRel("all-details"));
        return entityModel;
    }
    //Need to implement find by id logic for dynamic filtering
    @GetMapping(path="/asset/find/v2")
    public MappingJacksonValue findOned(){        
        List<AssetInfo> asetInfo=null;
        MappingJacksonValue mapping=new MappingJacksonValue(asetInfo);
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters=new SimpleFilterProvider().addFilter("somefilterbean", filter);
        mapping.setFilters(filters);
        return mapping;
    }

    //Implementing Internationalization
    /**
     * Internationalization -i18n
     * Rest api might have consumes from around the world
     * Typically HTTP Request Header "Accept-Language is used
     * "Accept-Language" indicates natural language and locale that the consumer prefers
     *    eg en -English
     *    nl -Dutch
     *    fr -french
     *     
     */
    @GetMapping("/internationalization")
    public String hello(){
        Locale locale=LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null,"Default Messsage", locale);
    }
    
    //VERSIONING
    /*
     * Variety of options :-
     * ->URL -- In this case we will create different urls for the different versions
     * ->REQUEST PARAMETERS --By using either params or @RequestParam
     * ->HEADER -- headers=X-API-VERSION=1 OR headers=X-API-VERSION=2
     * ->MEDIA TYPE -- "Accept"
     */

   //---- URL --------------------------
    @GetMapping("/versioning")    
    public String versioning(){
        return "heelo";
    }
    @GetMapping("/versioning/v2")    
    public String versioningv2(){
        return "heelo good morning";
    }
    //---- REQUEST PARAMETERS-----------
    @GetMapping(path="/versioning",params = "version=1")
    public String versioning_RequestParam_v1(){
        return "heelo";
    }
    @GetMapping(path="/versioning",params = "version=2")
    public String versioning_RequestParam_v2(){
        return "heelo";
    }
    @GetMapping("/versioning/params")
    public String versioning_RequestParam_newway(@RequestParam String v1){
        return "heelo" +v1;
    }
    //---- HEADERS -----------
    @GetMapping(path="/versioning/header", headers="X-API-VERSION=1")
    public String versioning_Requestheaders_v1(){
        return "heelo";
    }
    @GetMapping(path="/versioning/header", headers="X-API-VERSION=2")
    public String versioning_Requestheaders_v2(){
        return "heelo_world";
    }
    //---- MEDIA TYPE -----------
    @GetMapping(path="/versioning/media", produces ="application/vn.company.app-v1+json")
    public String versioning_Requestmedia_v1(){
        return "heelo";
    }
    @GetMapping(path="/versioning/media", produces ="application/vn.company.app-v2+json")
    public String versioning_Requestmedia_v2(){
        return "heelo_world";
    }
}
