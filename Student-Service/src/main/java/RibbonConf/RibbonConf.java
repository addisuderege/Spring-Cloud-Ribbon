package RibbonConf;

import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@Configuration
public class RibbonConf {
	
	public IRule getLoadBalnce(){
		return new WeightedResponseTimeRule();
	}
	
	public IPing getPing(){
		return new PingUrl();
	}
}
