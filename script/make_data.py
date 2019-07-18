# 为zeus项目制造假数据
# pip3 install Faker
# pip3 install PyMysql
from faker import Faker
import pymysql.cursors

fake = Faker()
fake_cn = Faker("zh_CN")


# Connect to the database
connection = pymysql.connect(host='127.0.0.1',
                             user='root',
                             password='',
                             db='zeus',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)
cursor = connection.cursor()


def user():
    """制造用户假数据"""
    for i in range(100):
        sql = "INSERT INTO `system_user` (`username`, `user_id`, `email`, `mobile`) VALUES ('{0}', '{1}', '{2}', '{3}')".format(fake_cn.name(), fake.name(), fake.email(), fake_cn.phone_number())
        cursor.execute(sql)
        connection.commit()


if __name__ == "__main__":
    user()