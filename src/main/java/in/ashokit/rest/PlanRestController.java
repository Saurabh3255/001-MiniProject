package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.Plan;
import in.ashokit.props.AppProperties;
import in.ashokit.service.PlanServie;

@RestController
public class PlanRestController
{
	private PlanServie planService;

	public Map<String, String> messages;
	
	public PlanRestController(PlanServie planService,AppProperties appProp)
	{
		this.planService=planService;
		this.messages=appProp.getMessages();
	}
	
	  
	@GetMapping("/catigories")
	public ResponseEntity<Map<Integer, String>> planCatgories()
	{
		Map<Integer, String> planCategory = planService.getPlanCategory(); 
		
		return new ResponseEntity<>(planCategory,HttpStatus.OK);
	}
	
	@PostMapping("/saveplan")
	public ResponseEntity<String> savePlan(@RequestBody  Plan plan)
	{
		boolean isSaved= planService.savePlan(plan);
		
		String Responsemsg=AppConstants.EMPTY_STR;
		
		if(isSaved)
		{
			
			Responsemsg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		}
		else
		{
			Responsemsg= messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		
		return new ResponseEntity<>(Responsemsg,HttpStatus.CREATED);
	}
	
	@GetMapping("/getplan")
	public ResponseEntity<List<Plan>> getPlans()
	{
		List<Plan> list = planService.getAllPlan();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId)
	{
		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<>(planById,HttpStatus.OK);
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan)
	{
		boolean isUpdated=planService.updatePlan(plan);
		String Msg = AppConstants.EMPTY_STR;
	
		if(isUpdated)
		{
			Msg= messages.get(AppConstants.PLAN_UPDATED_SAVE);
		}
		else
		{
			Msg= messages.get(AppConstants.PLAN_UPDATED_FAIL);
		}
		
		return new ResponseEntity<>(Msg,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId)
	{	
		boolean deletePlanById = planService.deletePlanById(planId);
		String Msg= AppConstants.EMPTY_STR;
	
		if(deletePlanById)
		{
			Msg=messages.get(AppConstants.PLAN_DELETED_SUCC);
		}
		else
		{
			Msg=messages.get(AppConstants.PLAN_DELETED_FAIL);
		}
		
		return new ResponseEntity<>(Msg,HttpStatus.OK);
	}
	
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> planStatusChnage(@PathVariable Integer planId, @PathVariable String status)
	{
		boolean isStatusChnage=planService.planStatusChange(planId,status);
		
		String Msg = AppConstants.EMPTY_STR;
		
		if(isStatusChnage)
		{
			Msg=messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCC);
		}
		else
		{
			Msg=messages.get(AppConstants.PLAN_UPDATED_FAIL);
		}
		
		return new ResponseEntity<>(Msg,HttpStatus.OK);
		
	}
	
	
}
