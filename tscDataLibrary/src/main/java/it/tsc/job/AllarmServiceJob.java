/**
 * 
 */
package it.tsc.job;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import it.tsc.service.AllarmService;

/**
 * @author astraservice Job for allarm watcher
 */
@Service("allarmServiceJob")
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
@Scope(value = "singleton")
public class AllarmServiceJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(AllarmServiceJob.class);

	@SuppressWarnings("unused")
	@Autowired
	private AllarmService allarmService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private Map jobDataMap;

	public AllarmServiceJob() {
		super();
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		// send websocket event
		Validate.notNull(jobDataMap, "jobDataMap cannot be null");
		AllarmNotifier allarmNotifier = null;
		if (jobDataMap.containsKey("allarmNotifier")
				|| (jobDataMap.containsKey("allarmNotifier") && jobDataMap.get("allarmNotifier") != null)) {
			allarmNotifier = (AllarmNotifier) jobDataMap.get("allarmNotifier");
		}
		if (allarmNotifier != null) {
			String msg = allarmService.jsonGetAllarms();
			if (StringUtils.isNotEmpty(msg) && !"[]".equals(msg)) {
				logger.debug("msg allarm: {}", msg);
				allarmNotifier.onAllarmReceived(msg);
			}
		}
	}

}
