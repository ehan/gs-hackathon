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

package com.liferay.gs.hack.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException;
import com.liferay.gs.hack.model.TimesheetApproval;
import com.liferay.gs.hack.model.impl.TimesheetApprovalImpl;
import com.liferay.gs.hack.model.impl.TimesheetApprovalModelImpl;
import com.liferay.gs.hack.service.persistence.TimesheetApprovalPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the timesheet approval service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TimesheetApprovalPersistence
 * @see com.liferay.gs.hack.service.persistence.TimesheetApprovalUtil
 * @generated
 */
@ProviderType
public class TimesheetApprovalPersistenceImpl extends BasePersistenceImpl<TimesheetApproval>
	implements TimesheetApprovalPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TimesheetApprovalUtil} to access the timesheet approval persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TimesheetApprovalImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED,
			TimesheetApprovalImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED,
			TimesheetApprovalImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED,
			TimesheetApprovalImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED,
			TimesheetApprovalImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TimesheetApprovalModelImpl.UUID_COLUMN_BITMASK |
			TimesheetApprovalModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the timesheet approvals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet approvals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @return the range of matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet approvals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid(String uuid, int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the timesheet approvals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid(String uuid, int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<TimesheetApproval> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetApproval>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimesheetApproval timesheetApproval : list) {
					if (!Objects.equals(uuid, timesheetApproval.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TIMESHEETAPPROVAL_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TimesheetApprovalModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<TimesheetApproval>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetApproval>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timesheet approval in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval findByUuid_First(String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = fetchByUuid_First(uuid,
				orderByComparator);

		if (timesheetApproval != null) {
			return timesheetApproval;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetApprovalException(msg.toString());
	}

	/**
	 * Returns the first timesheet approval in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval fetchByUuid_First(String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		List<TimesheetApproval> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timesheet approval in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval findByUuid_Last(String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = fetchByUuid_Last(uuid,
				orderByComparator);

		if (timesheetApproval != null) {
			return timesheetApproval;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetApprovalException(msg.toString());
	}

	/**
	 * Returns the last timesheet approval in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval fetchByUuid_Last(String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<TimesheetApproval> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timesheet approvals before and after the current timesheet approval in the ordered set where uuid = &#63;.
	 *
	 * @param timesheetApprovalId the primary key of the current timesheet approval
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval[] findByUuid_PrevAndNext(
		long timesheetApprovalId, String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = findByPrimaryKey(timesheetApprovalId);

		Session session = null;

		try {
			session = openSession();

			TimesheetApproval[] array = new TimesheetApprovalImpl[3];

			array[0] = getByUuid_PrevAndNext(session, timesheetApproval, uuid,
					orderByComparator, true);

			array[1] = timesheetApproval;

			array[2] = getByUuid_PrevAndNext(session, timesheetApproval, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimesheetApproval getByUuid_PrevAndNext(Session session,
		TimesheetApproval timesheetApproval, String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TIMESHEETAPPROVAL_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimesheetApprovalModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(timesheetApproval);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TimesheetApproval> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timesheet approvals where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (TimesheetApproval timesheetApproval : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(timesheetApproval);
		}
	}

	/**
	 * Returns the number of timesheet approvals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching timesheet approvals
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TIMESHEETAPPROVAL_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "timesheetApproval.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "timesheetApproval.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(timesheetApproval.uuid IS NULL OR timesheetApproval.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED,
			TimesheetApprovalImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			TimesheetApprovalModelImpl.UUID_COLUMN_BITMASK |
			TimesheetApprovalModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the timesheet approval where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTimesheetApprovalException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval findByUUID_G(String uuid, long groupId)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = fetchByUUID_G(uuid, groupId);

		if (timesheetApproval == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTimesheetApprovalException(msg.toString());
		}

		return timesheetApproval;
	}

	/**
	 * Returns the timesheet approval where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the timesheet approval where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof TimesheetApproval) {
			TimesheetApproval timesheetApproval = (TimesheetApproval)result;

			if (!Objects.equals(uuid, timesheetApproval.getUuid()) ||
					(groupId != timesheetApproval.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TIMESHEETAPPROVAL_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<TimesheetApproval> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					TimesheetApproval timesheetApproval = list.get(0);

					result = timesheetApproval;

					cacheResult(timesheetApproval);

					if ((timesheetApproval.getUuid() == null) ||
							!timesheetApproval.getUuid().equals(uuid) ||
							(timesheetApproval.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, timesheetApproval);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (TimesheetApproval)result;
		}
	}

	/**
	 * Removes the timesheet approval where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the timesheet approval that was removed
	 */
	@Override
	public TimesheetApproval removeByUUID_G(String uuid, long groupId)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = findByUUID_G(uuid, groupId);

		return remove(timesheetApproval);
	}

	/**
	 * Returns the number of timesheet approvals where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching timesheet approvals
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TIMESHEETAPPROVAL_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "timesheetApproval.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "timesheetApproval.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(timesheetApproval.uuid IS NULL OR timesheetApproval.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "timesheetApproval.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED,
			TimesheetApprovalImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED,
			TimesheetApprovalImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			TimesheetApprovalModelImpl.UUID_COLUMN_BITMASK |
			TimesheetApprovalModelImpl.COMPANYID_COLUMN_BITMASK |
			TimesheetApprovalModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @return the range of matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<TimesheetApproval> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetApproval>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimesheetApproval timesheetApproval : list) {
					if (!Objects.equals(uuid, timesheetApproval.getUuid()) ||
							(companyId != timesheetApproval.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TIMESHEETAPPROVAL_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TimesheetApprovalModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<TimesheetApproval>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetApproval>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (timesheetApproval != null) {
			return timesheetApproval;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetApprovalException(msg.toString());
	}

	/**
	 * Returns the first timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		List<TimesheetApproval> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (timesheetApproval != null) {
			return timesheetApproval;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetApprovalException(msg.toString());
	}

	/**
	 * Returns the last timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	 */
	@Override
	public TimesheetApproval fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<TimesheetApproval> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timesheet approvals before and after the current timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param timesheetApprovalId the primary key of the current timesheet approval
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval[] findByUuid_C_PrevAndNext(
		long timesheetApprovalId, String uuid, long companyId,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = findByPrimaryKey(timesheetApprovalId);

		Session session = null;

		try {
			session = openSession();

			TimesheetApproval[] array = new TimesheetApprovalImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, timesheetApproval,
					uuid, companyId, orderByComparator, true);

			array[1] = timesheetApproval;

			array[2] = getByUuid_C_PrevAndNext(session, timesheetApproval,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimesheetApproval getByUuid_C_PrevAndNext(Session session,
		TimesheetApproval timesheetApproval, String uuid, long companyId,
		OrderByComparator<TimesheetApproval> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TIMESHEETAPPROVAL_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimesheetApprovalModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(timesheetApproval);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TimesheetApproval> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timesheet approvals where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (TimesheetApproval timesheetApproval : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(timesheetApproval);
		}
	}

	/**
	 * Returns the number of timesheet approvals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching timesheet approvals
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TIMESHEETAPPROVAL_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "timesheetApproval.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "timesheetApproval.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(timesheetApproval.uuid IS NULL OR timesheetApproval.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "timesheetApproval.companyId = ?";

	public TimesheetApprovalPersistenceImpl() {
		setModelClass(TimesheetApproval.class);
	}

	/**
	 * Caches the timesheet approval in the entity cache if it is enabled.
	 *
	 * @param timesheetApproval the timesheet approval
	 */
	@Override
	public void cacheResult(TimesheetApproval timesheetApproval) {
		entityCache.putResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalImpl.class, timesheetApproval.getPrimaryKey(),
			timesheetApproval);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				timesheetApproval.getUuid(), timesheetApproval.getGroupId()
			}, timesheetApproval);

		timesheetApproval.resetOriginalValues();
	}

	/**
	 * Caches the timesheet approvals in the entity cache if it is enabled.
	 *
	 * @param timesheetApprovals the timesheet approvals
	 */
	@Override
	public void cacheResult(List<TimesheetApproval> timesheetApprovals) {
		for (TimesheetApproval timesheetApproval : timesheetApprovals) {
			if (entityCache.getResult(
						TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
						TimesheetApprovalImpl.class,
						timesheetApproval.getPrimaryKey()) == null) {
				cacheResult(timesheetApproval);
			}
			else {
				timesheetApproval.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all timesheet approvals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TimesheetApprovalImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the timesheet approval.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TimesheetApproval timesheetApproval) {
		entityCache.removeResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalImpl.class, timesheetApproval.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TimesheetApprovalModelImpl)timesheetApproval);
	}

	@Override
	public void clearCache(List<TimesheetApproval> timesheetApprovals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TimesheetApproval timesheetApproval : timesheetApprovals) {
			entityCache.removeResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
				TimesheetApprovalImpl.class, timesheetApproval.getPrimaryKey());

			clearUniqueFindersCache((TimesheetApprovalModelImpl)timesheetApproval);
		}
	}

	protected void cacheUniqueFindersCache(
		TimesheetApprovalModelImpl timesheetApprovalModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					timesheetApprovalModelImpl.getUuid(),
					timesheetApprovalModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				timesheetApprovalModelImpl);
		}
		else {
			if ((timesheetApprovalModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetApprovalModelImpl.getUuid(),
						timesheetApprovalModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					timesheetApprovalModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		TimesheetApprovalModelImpl timesheetApprovalModelImpl) {
		Object[] args = new Object[] {
				timesheetApprovalModelImpl.getUuid(),
				timesheetApprovalModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((timesheetApprovalModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					timesheetApprovalModelImpl.getOriginalUuid(),
					timesheetApprovalModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new timesheet approval with the primary key. Does not add the timesheet approval to the database.
	 *
	 * @param timesheetApprovalId the primary key for the new timesheet approval
	 * @return the new timesheet approval
	 */
	@Override
	public TimesheetApproval create(long timesheetApprovalId) {
		TimesheetApproval timesheetApproval = new TimesheetApprovalImpl();

		timesheetApproval.setNew(true);
		timesheetApproval.setPrimaryKey(timesheetApprovalId);

		String uuid = PortalUUIDUtil.generate();

		timesheetApproval.setUuid(uuid);

		timesheetApproval.setCompanyId(companyProvider.getCompanyId());

		return timesheetApproval;
	}

	/**
	 * Removes the timesheet approval with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param timesheetApprovalId the primary key of the timesheet approval
	 * @return the timesheet approval that was removed
	 * @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval remove(long timesheetApprovalId)
		throws NoSuchTimesheetApprovalException {
		return remove((Serializable)timesheetApprovalId);
	}

	/**
	 * Removes the timesheet approval with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the timesheet approval
	 * @return the timesheet approval that was removed
	 * @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval remove(Serializable primaryKey)
		throws NoSuchTimesheetApprovalException {
		Session session = null;

		try {
			session = openSession();

			TimesheetApproval timesheetApproval = (TimesheetApproval)session.get(TimesheetApprovalImpl.class,
					primaryKey);

			if (timesheetApproval == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTimesheetApprovalException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(timesheetApproval);
		}
		catch (NoSuchTimesheetApprovalException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TimesheetApproval removeImpl(TimesheetApproval timesheetApproval) {
		timesheetApproval = toUnwrappedModel(timesheetApproval);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(timesheetApproval)) {
				timesheetApproval = (TimesheetApproval)session.get(TimesheetApprovalImpl.class,
						timesheetApproval.getPrimaryKeyObj());
			}

			if (timesheetApproval != null) {
				session.delete(timesheetApproval);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (timesheetApproval != null) {
			clearCache(timesheetApproval);
		}

		return timesheetApproval;
	}

	@Override
	public TimesheetApproval updateImpl(TimesheetApproval timesheetApproval) {
		timesheetApproval = toUnwrappedModel(timesheetApproval);

		boolean isNew = timesheetApproval.isNew();

		TimesheetApprovalModelImpl timesheetApprovalModelImpl = (TimesheetApprovalModelImpl)timesheetApproval;

		if (Validator.isNull(timesheetApproval.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			timesheetApproval.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (timesheetApproval.getCreateDate() == null)) {
			if (serviceContext == null) {
				timesheetApproval.setCreateDate(now);
			}
			else {
				timesheetApproval.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!timesheetApprovalModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				timesheetApproval.setModifiedDate(now);
			}
			else {
				timesheetApproval.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (timesheetApproval.isNew()) {
				session.save(timesheetApproval);

				timesheetApproval.setNew(false);
			}
			else {
				timesheetApproval = (TimesheetApproval)session.merge(timesheetApproval);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TimesheetApprovalModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((timesheetApprovalModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetApprovalModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { timesheetApprovalModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((timesheetApprovalModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetApprovalModelImpl.getOriginalUuid(),
						timesheetApprovalModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						timesheetApprovalModelImpl.getUuid(),
						timesheetApprovalModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetApprovalImpl.class, timesheetApproval.getPrimaryKey(),
			timesheetApproval, false);

		clearUniqueFindersCache(timesheetApprovalModelImpl);
		cacheUniqueFindersCache(timesheetApprovalModelImpl, isNew);

		timesheetApproval.resetOriginalValues();

		return timesheetApproval;
	}

	protected TimesheetApproval toUnwrappedModel(
		TimesheetApproval timesheetApproval) {
		if (timesheetApproval instanceof TimesheetApprovalImpl) {
			return timesheetApproval;
		}

		TimesheetApprovalImpl timesheetApprovalImpl = new TimesheetApprovalImpl();

		timesheetApprovalImpl.setNew(timesheetApproval.isNew());
		timesheetApprovalImpl.setPrimaryKey(timesheetApproval.getPrimaryKey());

		timesheetApprovalImpl.setUuid(timesheetApproval.getUuid());
		timesheetApprovalImpl.setTimesheetApprovalId(timesheetApproval.getTimesheetApprovalId());
		timesheetApprovalImpl.setGroupId(timesheetApproval.getGroupId());
		timesheetApprovalImpl.setTimesheetId(timesheetApproval.getTimesheetId());
		timesheetApprovalImpl.setCompanyId(timesheetApproval.getCompanyId());
		timesheetApprovalImpl.setUserId(timesheetApproval.getUserId());
		timesheetApprovalImpl.setUserName(timesheetApproval.getUserName());
		timesheetApprovalImpl.setCreateDate(timesheetApproval.getCreateDate());
		timesheetApprovalImpl.setModifiedDate(timesheetApproval.getModifiedDate());
		timesheetApprovalImpl.setStatus(timesheetApproval.getStatus());
		timesheetApprovalImpl.setComment(timesheetApproval.getComment());

		return timesheetApprovalImpl;
	}

	/**
	 * Returns the timesheet approval with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the timesheet approval
	 * @return the timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTimesheetApprovalException {
		TimesheetApproval timesheetApproval = fetchByPrimaryKey(primaryKey);

		if (timesheetApproval == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTimesheetApprovalException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return timesheetApproval;
	}

	/**
	 * Returns the timesheet approval with the primary key or throws a {@link NoSuchTimesheetApprovalException} if it could not be found.
	 *
	 * @param timesheetApprovalId the primary key of the timesheet approval
	 * @return the timesheet approval
	 * @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval findByPrimaryKey(long timesheetApprovalId)
		throws NoSuchTimesheetApprovalException {
		return findByPrimaryKey((Serializable)timesheetApprovalId);
	}

	/**
	 * Returns the timesheet approval with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the timesheet approval
	 * @return the timesheet approval, or <code>null</code> if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
				TimesheetApprovalImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TimesheetApproval timesheetApproval = (TimesheetApproval)serializable;

		if (timesheetApproval == null) {
			Session session = null;

			try {
				session = openSession();

				timesheetApproval = (TimesheetApproval)session.get(TimesheetApprovalImpl.class,
						primaryKey);

				if (timesheetApproval != null) {
					cacheResult(timesheetApproval);
				}
				else {
					entityCache.putResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
						TimesheetApprovalImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
					TimesheetApprovalImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return timesheetApproval;
	}

	/**
	 * Returns the timesheet approval with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param timesheetApprovalId the primary key of the timesheet approval
	 * @return the timesheet approval, or <code>null</code> if a timesheet approval with the primary key could not be found
	 */
	@Override
	public TimesheetApproval fetchByPrimaryKey(long timesheetApprovalId) {
		return fetchByPrimaryKey((Serializable)timesheetApprovalId);
	}

	@Override
	public Map<Serializable, TimesheetApproval> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TimesheetApproval> map = new HashMap<Serializable, TimesheetApproval>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TimesheetApproval timesheetApproval = fetchByPrimaryKey(primaryKey);

			if (timesheetApproval != null) {
				map.put(primaryKey, timesheetApproval);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
					TimesheetApprovalImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TimesheetApproval)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TIMESHEETAPPROVAL_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (TimesheetApproval timesheetApproval : (List<TimesheetApproval>)q.list()) {
				map.put(timesheetApproval.getPrimaryKeyObj(), timesheetApproval);

				cacheResult(timesheetApproval);

				uncachedPrimaryKeys.remove(timesheetApproval.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TimesheetApprovalModelImpl.ENTITY_CACHE_ENABLED,
					TimesheetApprovalImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the timesheet approvals.
	 *
	 * @return the timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @return the range of timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findAll(int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the timesheet approvals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timesheet approvals
	 * @param end the upper bound of the range of timesheet approvals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of timesheet approvals
	 */
	@Override
	public List<TimesheetApproval> findAll(int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<TimesheetApproval> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetApproval>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TIMESHEETAPPROVAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TIMESHEETAPPROVAL;

				if (pagination) {
					sql = sql.concat(TimesheetApprovalModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TimesheetApproval>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetApproval>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the timesheet approvals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TimesheetApproval timesheetApproval : findAll()) {
			remove(timesheetApproval);
		}
	}

	/**
	 * Returns the number of timesheet approvals.
	 *
	 * @return the number of timesheet approvals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TIMESHEETAPPROVAL);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TimesheetApprovalModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the timesheet approval persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TimesheetApprovalImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_TIMESHEETAPPROVAL = "SELECT timesheetApproval FROM TimesheetApproval timesheetApproval";
	private static final String _SQL_SELECT_TIMESHEETAPPROVAL_WHERE_PKS_IN = "SELECT timesheetApproval FROM TimesheetApproval timesheetApproval WHERE timesheetApprovalId IN (";
	private static final String _SQL_SELECT_TIMESHEETAPPROVAL_WHERE = "SELECT timesheetApproval FROM TimesheetApproval timesheetApproval WHERE ";
	private static final String _SQL_COUNT_TIMESHEETAPPROVAL = "SELECT COUNT(timesheetApproval) FROM TimesheetApproval timesheetApproval";
	private static final String _SQL_COUNT_TIMESHEETAPPROVAL_WHERE = "SELECT COUNT(timesheetApproval) FROM TimesheetApproval timesheetApproval WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "timesheetApproval.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TimesheetApproval exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TimesheetApproval exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TimesheetApprovalPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "comment"
			});
}