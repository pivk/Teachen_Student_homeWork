package doc.common.application;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationContextAware{
	protected Logger logger = LogManager.getLogger(StartupListener.class);
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		
		logger.debug("==OA==云盘系统启动");
		doc.system.service.ConfigService configService=new doc.system.service.ConfigService();
		configService.init();
	}

}
