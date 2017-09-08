# 4th_Memo
java로 메모장을 만들어봅시다~


# 1. 테이블 생성
# create table 테이블이름 ( 
#    컬럼1이름 컬럼타입 옵션1 옵션2,
#    컬럼2이름 컬럼타입,
#    컬럼3이름 컬럼타입
#);
create table memo (
	`no` int primary key not null auto_increment,
    `name` varchar(100),
    `content` text,
    `datetime` datetime
);

# 2. 입력
# insert into 테이블명(컬럼1, 컬럼2, 컬럼3)
# values('문자열',222,now());
# 자동증가 컬럼에는 값을 넣지 않는다
insert into memo(name, content, datetime) values('지코','내용을 여기', now());
commit;

# 3. 읽기
# select 컬럼1, 컬럼2, 컬럼3 from 테이블명 
#  where 조건절
# 전체 데이터를 가져올 때는 where 조건절 생략
select name, content, datetime from memo;
# 조건절 사용 
# 1. 정확한 단어 매칭
#where name = '지코'
# 2. 포함되는 단어 검색
#where name like '%검색어%'
# 3. 값의 범위를 검색
#where datetime between '2017-01-01' and '2017-08-31'

# 4. 수정 
# update 테이블명 set 변경할컬럼1 = '변경값'
#                     ,변경할컬럼2 = 변경값
#               where 특정컬럼명 = '어떤값'
update memo set content = '수정된 내용입니다'
          where name = '개코';
          
# 5. 삭제
# delete from 테이블명 where 컬러명 = 값
delete from memo where name = '우원재'
