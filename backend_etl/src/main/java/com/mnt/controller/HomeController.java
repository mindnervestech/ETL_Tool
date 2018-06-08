package com.mnt.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mnt.service.SaveFileService;
import com.mnt.vm.MappingVM;

@RestController
public class HomeController {

	@Autowired
	SaveFileService saveFileService;
	
	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! It's working";
    }
	
	@RequestMapping(value="/savemapping",method = RequestMethod.POST)
	@ResponseBody
    public void saveMapping(@RequestParam(value="provider") String provider,
    		@RequestParam(value="filename") String filename, @RequestParam(value="mapping") String mappingJSON) {
		saveFileService.saveMapping(provider, filename, mappingJSON);
    }
	
	@RequestMapping(value="/testing",method = RequestMethod.GET)
	@ResponseBody
    public void test() {
		//saveFileService.insertIntoTable("stoploss_detail", new File("/home/anmol/csvreader_files/OPT_test/OPTAEGC_STOPLOSS_DETAIL.txt"), "2018", "june","OPT");
		//saveFileService.deleteFromTable("stoploss_detail", "2018", "june", "OPT");
		saveFileService.saveFileDetail("specialty_claim_detail", new File("/home/anmol/csvreader_files/OPT_test/OPTAEGC_SPECIALTY_CLAIM_DETAIL.txt"), "2018", "june", "OPT", new Date());
	}
	
	@RequestMapping(value="/getProviders",method = RequestMethod.GET)
	@ResponseBody
    public Map<String,List<String>> getProviders() {
        return saveFileService.getProviders();
    }
	
	@RequestMapping(value="/getProvidersFTP",method = RequestMethod.GET)
	@ResponseBody
    public Map<String,List<String>> getProvidersFTP() {
        return saveFileService.getProvidersFTP();
    }
	
	@RequestMapping(value="/getMappingData",method = RequestMethod.GET)
	@ResponseBody
    public MappingVM getMappingData(@RequestParam(value="provider") String provider,
    		@RequestParam(value="filename") String filename) {
        return saveFileService.getMappingData(provider, filename);
    }
	
	/*
	@RequestMapping(value="/saveDemographicDetails",method = RequestMethod.POST)
	@ResponseBody
    public String saveDemographicDetails() {
		//saveFileService.saveFileDetail("demographic_detail",fileHandle, yearDir.getName(), monthDir.getName(), providerDir.getName(), fileLastModifiedTime);
        return "success";
    }
	
	@RequestMapping(value="/saveInstClaimDetails",method = RequestMethod.POST)
	@ResponseBody
    public String saveInstClaimDetails() {
		//saveFileService.saveFileDetail("inst_claim_detail",fileHandle, yearDir.getName(), monthDir.getName(), providerDir.getName(), fileLastModifiedTime);
        return "success";
    }
	
	@RequestMapping(value="/savePremiumDetails",method = RequestMethod.POST)
	@ResponseBody
    public String savePremiumDetails() {
		//saveFileService.saveFileDetail("premium_detail", fileHandle, yearDir.getName(), monthDir.getName(), providerDir.getName(), fileLastModifiedTime);
        return "success";
    }
	
	@RequestMapping(value="/saveProfClaimDetails",method = RequestMethod.POST)
	@ResponseBody
    public String saveProfClaimDetails() {
		//saveFileService.saveFileDetail("prof_claim_detail", fileHandle, yearDir.getName(), monthDir.getName(), providerDir.getName(), fileLastModifiedTime);
        return "success";
    }
	
	@RequestMapping(value="/saveRxDetails",method = RequestMethod.POST)
	@ResponseBody
    public String saveRxDetails() {
		//saveFileService.saveFileDetail("rx_detail", fileHandle, yearDir.getName(), monthDir.getName(), providerDir.getName(), fileLastModifiedTime);
        return "success";
    }
	
	@RequestMapping(value="/saveSpecialtyClaimDetails",method = RequestMethod.POST)
	@ResponseBody
    public String saveSpecialtyClaimDetails() {
		//saveFileService.saveFileDetail("specialty_claim_detail", fileHandle, yearDir.getName(), monthDir.getName(), providerDir.getName(), fileLastModifiedTime);
        return "success";
    }
	
	@RequestMapping(value="/saveStoplossDetails",method = RequestMethod.POST)
	@ResponseBody
    public String saveStoplossDetails() {
		//saveFileService.saveFileDetail("stoploss_detail",fileHandle, yearDir.getName(), monthDir.getName(), providerDir.getName(), fileLastModifiedTime);
        return "success";
    }
	*/
}
