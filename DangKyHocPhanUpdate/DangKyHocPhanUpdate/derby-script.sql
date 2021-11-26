create table Khoa (
    MaKhoa varchar(4) primary key,
    TenKhoa varchar(100)
);
insert into Khoa values ('FIT', 'Khoa CNTT'),
    ('Dien', 'Khoa Điện'),
    ('FME', 'Khoa Cơ Khí');
create table ChuyenNganh(
    MaNganh varchar(4) primary key,
    TenNganh varchar(100),
	MaKhoa varchar(4),
    foreign key (MaKhoa) references Khoa (MaKhoa)
);
insert into ChuyenNganh
values ('CNTT', 'Công nghệ thông tin', 'FIT'),
    ('HTTT', 'Hệ thống thông tin', 'FIT');
create table SinhVien(
    MaSV varchar(10) primary key,
    HoTen varchar(100),
    NgaySinh date,
    GioiTinh boolean,
    Sdt varchar(10),
    DiaChi varchar(200),
    MaNganh varchar(4),
    foreign key (MaNganh) references ChuyenNganh(MaNganh)
);
insert into SinhVien
values (
        '2018602017',
        'Ngô Việt Chung',
        '2000-06-17',
        true,
        '0393417020',
        'Hải Dương',
        'CNTT'
    ),
    (
        '2018602018',
        'Trần Sỹ Danh',
        '2000-11-17',
        true,
        '0393417020',
        'Thanh Hóa',
        'CNTT'
    );
create table GiangVien(
    MaGV varchar(10) primary key,
    HoTen varchar(100),
    NgaySinh date,
    GioiTinh boolean,
    DiaChi varchar(200),
    Sdt varchar(10),
    MaKhoa varchar(4),
    foreign key (MaKhoa) references Khoa (MaKhoa)
);
insert into GiangVien
values (
        '2012602012',
        'Nguyễn Thị Nhung',
        '1992-12-12',
        false,
        'Hà Nội',
        '123456789',
        'FIT'
    ),
    (
        '2012602013',
        'Nguyễn Tuấn Tú',
        '1992-12-12',
        true,
        'Hà Nội',
        '123456789',
        'FIT'
    );
create table HocPhan(
    MaHP varchar(6) primary key,
    MaHPTD varchar(6),
    TenHP varchar(100),
    MoTa varchar(200),
    SoTC int,
    PPDG varchar(100),
    BatBuoc boolean,
    foreign key (MaHPTD) references HocPhan (MaHP) on delete set null
);

insert into HocPhan values ('HP0001',null,'Lập trình Windows','Lập trình Windows',3,'Thực hành',true),
('HP0002',null,'Phân tích thiết kế hệ thống','Phân tích thiết kế hệ thống',3,'Bài tập lớn',true),
('HP0003',null,'Pháp luật đại cương','Pháp luật đại cương',2,'Lý thuyết',true);

create table ChuongTrinh(
    MaHP varchar(6),
    MaNganh varchar(4),
    primary key (MaHP,MaNganh),
    foreign key (MaHP) references HocPhan (MaHP),
    foreign key (MaNganh) references ChuyenNganh (MaNganh)
);

insert into ChuongTrinh values ('HP0001','CNTT'),('HP0002','HTTT'),('HP0003','CNTT'),('HP0003','HTTT');

create table LopHP(
    MaLHP varchar(10) primary key,
    Thu varchar(30),
    Tiet varchar(30),
    Phong varchar(30),
    HocKy varchar(4),
    NamHoc varchar(10),
    MaHP varchar(6),
    NgayBatDau date,
    foreign key (MaHP) references HocPhan (MaHP) on delete cascade
);

insert into LopHP values
('2018600001','Hai','1,2,3,4','305-A9','I','2020-2021','HP0001','2020-08-21'),
('2018600002','Năm','4,5,6,7','306-A9','I','2020-2021','HP0002','2020-08-21');

create table DangKyGiangDay(
    MaGV varchar(10),
    MaLHP varchar(10),
    NgayDK date,
    primary key(MaGV,MaLHP),
    foreign key (MaGV) references GiangVien (MaGV),
    foreign key (MaLHP) references LopHP (MaLHP) on delete cascade
);

insert into DangKyGiangDay values 
('2012602012','2018600001','2020-08-15'),
('2012602013','2018600002','2020-08-15');

create table DangKyHoc (
    MaSV varchar(10),
    MaLHP varchar(10),
    NgayDK date,
    primary key (MaSV,MaLHP),
    foreign key (MaSV) references SinhVien (MaSV),
    foreign key (MaLHP) references LopHP (MaLHP) on delete cascade
);

insert into DangKyHoc values ('2018602017','2018600001','2020-08-15'),('2018602018','2018600001','2020-08-15');

create table NguoiDung(
    MaND varchar(10) primary key,
    MatKhau varchar(100),
    VaiTro varchar(2)
);

insert into NguoiDung values ('admin','root','AD');
insert into NguoiDung values ('2012002012','root','GV');
insert into NguoiDung values ('2018602017','root','SV');