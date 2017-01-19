package timesheet.details.web.portlet;

import com.liferay.gs.hack.model.Timesheet;
import com.liferay.gs.hack.model.TimesheetApproval;
import com.liferay.gs.hack.model.TimesheetTaskDuration;
import com.liferay.gs.hack.service.TimesheetApprovalLocalService;
import com.liferay.gs.hack.service.TimesheetLocalService;
import com.liferay.gs.hack.service.TimesheetService;
import com.liferay.gs.hack.service.TimesheetTaskDurationLocalService;
import com.liferay.gs.hack.service.TimesheetTaskDurationService;
import com.liferay.gs.hack.service.TimesheetTaskLocalService;
import com.liferay.gs.hack.service.TimesheetTaskService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.List;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=timesheet-details-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TimesheetDetailsWebPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		Timesheet timesheet = _timesheetLocalService.getTimesheet(0);

		List<TimesheetTaskDuration> timesheetTaskDurations =
			_timesheetTaskDurationLocalService.
				getTimesheetTaskDurationByTimesheetId(
					timesheet.getTimesheetId());

		List<TimesheetApproval> timesheetApprovals = _timesheetApprovalLocalService.gettime
		super.doView(renderRequest, renderResponse);
	}


	TimesheetLocalService _timesheetLocalService;

	TimesheetTaskDurationLocalService _timesheetTaskDurationLocalService;

	TimesheetApprovalLocalService _timesheetApprovalLocalService;
}