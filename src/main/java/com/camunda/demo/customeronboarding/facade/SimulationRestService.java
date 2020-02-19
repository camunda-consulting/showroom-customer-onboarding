package com.camunda.demo.customeronboarding.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.demo.customeronboarding.DemoData;

@RestController
@RequestMapping("API")
public class SimulationRestService {

  private DemoData demoData;
  
  public SimulationRestService(DemoData demoData) {
    this.demoData = demoData;
  }
  
  @GetMapping("/simulation/status")
  public boolean returnSimulationStatus() {
    return demoData.isSimulationFinished();
  }
  
}
