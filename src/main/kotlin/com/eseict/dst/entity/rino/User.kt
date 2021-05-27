package com.eseict.dst.entity.rino

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "OMS_USER_INFO", schema = "oms")
class User {
    @Id
    @Column(name = "USER_ID", length = 22, nullable = false, unique = true)
    var userId = ""

    @Column(name = "USER_LOGIN_ID", length = 50)
    var userLoginId = ""

    @Column(name = "USER_LOGIN_PSWD", length = 64)
    var userLoginPswd = ""

    @Column(name = "USER_NM", length = 50)
    var userNm = ""

    @Column(name = "PHONE", length = 50)
    var phone = ""

    @Column(name = "CP_NO", length = 50)
    var cpNo = ""

    @Column(name = "EMAIL", length = 100)
    var email = ""

    @Column(name = "FIST_REG_DTM", length = 17)
    var fistRegDtm = ""

    @Column(name = "LST_LOGIN_DTM", length = 17)
    var lstLoginDtm = ""

    @Column(name = "USE_YN", length = 1)
    var useYn = ""

    @Column(name = "DPT_ID", length = 22)
    var dptId: String? = null

    @Column(name = "ROlE_ID", length = 22, nullable = true)
    var roleId: String? = null

    @Column(name = "ENCRYPT_KEY", length = 50, nullable = true)
    var encryptKey: String? = null

    @Column(name = "login_try_count")
    var loginTryCount = 0

    @Column(name = "login_block_flag", length = 5)
    var loginBlockFlag: String? = null

    @Column(name = "EVENT_ROLE_ID", length = 50)
    var eventRoleId: String? = null

    @Column(name = "USER_GD", length = 50)
    var userGd: String? = null

    @Column(name = "CLIENT_CD", length = 3)
    var clientCd: String? = null

    @Column(name = "SITE_CD", length = 3)
    var siteCd: String? = null
}