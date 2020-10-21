create table report (
	report_id serial not null,
	created_at TIMESTAMP not null,
	modified_at TIMESTAMP,
	report_path text,
	status varchar(20) not null check(status in ('InProgress', 'Error', 'Completed')),
	constraint report_pk primary key(report_id)
);
