/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gs.hack.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.gs.hack.model.Timesheet;
import com.liferay.gs.hack.service.TimesheetLocalService;
import com.liferay.gs.hack.service.persistence.TimesheetApprovalPersistence;
import com.liferay.gs.hack.service.persistence.TimesheetPersistence;
import com.liferay.gs.hack.service.persistence.TimesheetTaskDurationPersistence;
import com.liferay.gs.hack.service.persistence.TimesheetTaskPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the timesheet local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.gs.hack.service.impl.TimesheetLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.gs.hack.service.impl.TimesheetLocalServiceImpl
 * @see com.liferay.gs.hack.service.TimesheetLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class TimesheetLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements TimesheetLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.gs.hack.service.TimesheetLocalServiceUtil} to access the timesheet local service.
	 */

	/**
	 * Adds the timesheet to the database. Also notifies the appropriate model listeners.
	 *
	 * @param timesheet the timesheet
	 * @return the timesheet that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Timesheet addTimesheet(Timesheet timesheet) {
		timesheet.setNew(true);

		return timesheetPersistence.update(timesheet);
	}

	/**
	 * Creates a new timesheet with the primary key. Does not add the timesheet to the database.
	 *
	 * @param timesheetId the primary key for the new timesheet
	 * @return the new timesheet
	 */
	@Override
	public Timesheet createTimesheet(long timesheetId) {
		return timesheetPersistence.create(timesheetId);
	}

	/**
	 * Deletes the timesheet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param timesheetId the primary key of the timesheet
	 * @return the timesheet that was removed
	 * @throws PortalException if a timesheet with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Timesheet deleteTimesheet(long timesheetId)
		throws PortalException {
		return timesheetPersistence.remove(timesheetId);
	}

	/**
	 * Deletes the timesheet from the database. Also notifies the appropriate model listeners.
	 *
	 * @param timesheet the timesheet
	 * @return the timesheet that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Timesheet deleteTimesheet(Timesheet timesheet) {
		return timesheetPersistence.remove(timesheet);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Timesheet.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return timesheetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gs.hack.model.impl.TimesheetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return timesheetPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gs.hack.model.impl.TimesheetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return timesheetPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return timesheetPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return timesheetPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public Timesheet fetchTimesheet(long timesheetId) {
		return timesheetPersistence.fetchByPrimaryKey(timesheetId);
	}

	/**
	 * Returns the timesheet matching the UUID and group.
	 *
	 * @param uuid the timesheet's UUID
	 * @param groupId the primary key of the group
	 * @return the matching timesheet, or <code>null</code> if a matching timesheet could not be found
	 */
	@Override
	public Timesheet fetchTimesheetByUuidAndGroupId(String uuid, long groupId) {
		return timesheetPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the timesheet with the primary key.
	 *
	 * @param timesheetId the primary key of the timesheet
	 * @return the timesheet
	 * @throws PortalException if a timesheet with the primary key could not be found
	 */
	@Override
	public Timesheet getTimesheet(long timesheetId) throws PortalException {
		return timesheetPersistence.findByPrimaryKey(timesheetId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(timesheetLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Timesheet.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("timesheetId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(timesheetLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Timesheet.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("timesheetId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(timesheetLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Timesheet.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("timesheetId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Timesheet>() {
				@Override
				public void performAction(Timesheet timesheet)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						timesheet);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(Timesheet.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return timesheetLocalService.deleteTimesheet((Timesheet)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return timesheetPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the timesheets matching the UUID and company.
	 *
	 * @param uuid the UUID of the timesheets
	 * @param companyId the primary key of the company
	 * @return the matching timesheets, or an empty list if no matches were found
	 */
	@Override
	public List<Timesheet> getTimesheetsByUuidAndCompanyId(String uuid,
		long companyId) {
		return timesheetPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of timesheets matching the UUID and company.
	 *
	 * @param uuid the UUID of the timesheets
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of timesheets
	 * @param end the upper bound of the range of timesheets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching timesheets, or an empty list if no matches were found
	 */
	@Override
	public List<Timesheet> getTimesheetsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Timesheet> orderByComparator) {
		return timesheetPersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the timesheet matching the UUID and group.
	 *
	 * @param uuid the timesheet's UUID
	 * @param groupId the primary key of the group
	 * @return the matching timesheet
	 * @throws PortalException if a matching timesheet could not be found
	 */
	@Override
	public Timesheet getTimesheetByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return timesheetPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the timesheets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gs.hack.model.impl.TimesheetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timesheets
	 * @param end the upper bound of the range of timesheets (not inclusive)
	 * @return the range of timesheets
	 */
	@Override
	public List<Timesheet> getTimesheets(int start, int end) {
		return timesheetPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of timesheets.
	 *
	 * @return the number of timesheets
	 */
	@Override
	public int getTimesheetsCount() {
		return timesheetPersistence.countAll();
	}

	/**
	 * Updates the timesheet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param timesheet the timesheet
	 * @return the timesheet that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Timesheet updateTimesheet(Timesheet timesheet) {
		return timesheetPersistence.update(timesheet);
	}

	/**
	 * Returns the timesheet local service.
	 *
	 * @return the timesheet local service
	 */
	public TimesheetLocalService getTimesheetLocalService() {
		return timesheetLocalService;
	}

	/**
	 * Sets the timesheet local service.
	 *
	 * @param timesheetLocalService the timesheet local service
	 */
	public void setTimesheetLocalService(
		TimesheetLocalService timesheetLocalService) {
		this.timesheetLocalService = timesheetLocalService;
	}

	/**
	 * Returns the timesheet persistence.
	 *
	 * @return the timesheet persistence
	 */
	public TimesheetPersistence getTimesheetPersistence() {
		return timesheetPersistence;
	}

	/**
	 * Sets the timesheet persistence.
	 *
	 * @param timesheetPersistence the timesheet persistence
	 */
	public void setTimesheetPersistence(
		TimesheetPersistence timesheetPersistence) {
		this.timesheetPersistence = timesheetPersistence;
	}

	/**
	 * Returns the timesheet approval local service.
	 *
	 * @return the timesheet approval local service
	 */
	public com.liferay.gs.hack.service.TimesheetApprovalLocalService getTimesheetApprovalLocalService() {
		return timesheetApprovalLocalService;
	}

	/**
	 * Sets the timesheet approval local service.
	 *
	 * @param timesheetApprovalLocalService the timesheet approval local service
	 */
	public void setTimesheetApprovalLocalService(
		com.liferay.gs.hack.service.TimesheetApprovalLocalService timesheetApprovalLocalService) {
		this.timesheetApprovalLocalService = timesheetApprovalLocalService;
	}

	/**
	 * Returns the timesheet approval persistence.
	 *
	 * @return the timesheet approval persistence
	 */
	public TimesheetApprovalPersistence getTimesheetApprovalPersistence() {
		return timesheetApprovalPersistence;
	}

	/**
	 * Sets the timesheet approval persistence.
	 *
	 * @param timesheetApprovalPersistence the timesheet approval persistence
	 */
	public void setTimesheetApprovalPersistence(
		TimesheetApprovalPersistence timesheetApprovalPersistence) {
		this.timesheetApprovalPersistence = timesheetApprovalPersistence;
	}

	/**
	 * Returns the timesheet task local service.
	 *
	 * @return the timesheet task local service
	 */
	public com.liferay.gs.hack.service.TimesheetTaskLocalService getTimesheetTaskLocalService() {
		return timesheetTaskLocalService;
	}

	/**
	 * Sets the timesheet task local service.
	 *
	 * @param timesheetTaskLocalService the timesheet task local service
	 */
	public void setTimesheetTaskLocalService(
		com.liferay.gs.hack.service.TimesheetTaskLocalService timesheetTaskLocalService) {
		this.timesheetTaskLocalService = timesheetTaskLocalService;
	}

	/**
	 * Returns the timesheet task persistence.
	 *
	 * @return the timesheet task persistence
	 */
	public TimesheetTaskPersistence getTimesheetTaskPersistence() {
		return timesheetTaskPersistence;
	}

	/**
	 * Sets the timesheet task persistence.
	 *
	 * @param timesheetTaskPersistence the timesheet task persistence
	 */
	public void setTimesheetTaskPersistence(
		TimesheetTaskPersistence timesheetTaskPersistence) {
		this.timesheetTaskPersistence = timesheetTaskPersistence;
	}

	/**
	 * Returns the timesheet task duration local service.
	 *
	 * @return the timesheet task duration local service
	 */
	public com.liferay.gs.hack.service.TimesheetTaskDurationLocalService getTimesheetTaskDurationLocalService() {
		return timesheetTaskDurationLocalService;
	}

	/**
	 * Sets the timesheet task duration local service.
	 *
	 * @param timesheetTaskDurationLocalService the timesheet task duration local service
	 */
	public void setTimesheetTaskDurationLocalService(
		com.liferay.gs.hack.service.TimesheetTaskDurationLocalService timesheetTaskDurationLocalService) {
		this.timesheetTaskDurationLocalService = timesheetTaskDurationLocalService;
	}

	/**
	 * Returns the timesheet task duration persistence.
	 *
	 * @return the timesheet task duration persistence
	 */
	public TimesheetTaskDurationPersistence getTimesheetTaskDurationPersistence() {
		return timesheetTaskDurationPersistence;
	}

	/**
	 * Sets the timesheet task duration persistence.
	 *
	 * @param timesheetTaskDurationPersistence the timesheet task duration persistence
	 */
	public void setTimesheetTaskDurationPersistence(
		TimesheetTaskDurationPersistence timesheetTaskDurationPersistence) {
		this.timesheetTaskDurationPersistence = timesheetTaskDurationPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.gs.hack.model.Timesheet",
			timesheetLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.gs.hack.model.Timesheet");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return TimesheetLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Timesheet.class;
	}

	protected String getModelClassName() {
		return Timesheet.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = timesheetPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = TimesheetLocalService.class)
	protected TimesheetLocalService timesheetLocalService;
	@BeanReference(type = TimesheetPersistence.class)
	protected TimesheetPersistence timesheetPersistence;
	@BeanReference(type = com.liferay.gs.hack.service.TimesheetApprovalLocalService.class)
	protected com.liferay.gs.hack.service.TimesheetApprovalLocalService timesheetApprovalLocalService;
	@BeanReference(type = TimesheetApprovalPersistence.class)
	protected TimesheetApprovalPersistence timesheetApprovalPersistence;
	@BeanReference(type = com.liferay.gs.hack.service.TimesheetTaskLocalService.class)
	protected com.liferay.gs.hack.service.TimesheetTaskLocalService timesheetTaskLocalService;
	@BeanReference(type = TimesheetTaskPersistence.class)
	protected TimesheetTaskPersistence timesheetTaskPersistence;
	@BeanReference(type = com.liferay.gs.hack.service.TimesheetTaskDurationLocalService.class)
	protected com.liferay.gs.hack.service.TimesheetTaskDurationLocalService timesheetTaskDurationLocalService;
	@BeanReference(type = TimesheetTaskDurationPersistence.class)
	protected TimesheetTaskDurationPersistence timesheetTaskDurationPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}