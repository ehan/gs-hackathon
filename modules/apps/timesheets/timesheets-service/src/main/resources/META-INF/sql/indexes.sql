create index IX_85B19D30 on gw_Timesheet (endDate);
create index IX_46BA6637 on gw_Timesheet (startDate);
create index IX_1F2132EF on gw_Timesheet (status[$COLUMN_LENGTH:75$]);
create index IX_CDE43EBD on gw_Timesheet (userId, startDate);
create index IX_1B46BEBD on gw_Timesheet (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6BF9D07F on gw_Timesheet (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A469C031 on gw_TimesheetApproval (timesheetId);
create index IX_71EB46A0 on gw_TimesheetApproval (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8CD4F522 on gw_TimesheetApproval (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_42550B2F on gw_TimesheetTask (timesheetId);
create index IX_C6ED2A62 on gw_TimesheetTask (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C520E164 on gw_TimesheetTask (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2BEFE2FB on gw_TimesheetTaskDuration (timesheetId);
create index IX_7407DB20 on gw_TimesheetTaskDuration (timesheetTaskId);
create index IX_B9D5216 on gw_TimesheetTaskDuration (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9F5F6618 on gw_TimesheetTaskDuration (uuid_[$COLUMN_LENGTH:75$], groupId);