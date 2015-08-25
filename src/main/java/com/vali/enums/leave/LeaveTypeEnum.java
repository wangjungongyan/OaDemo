package com.vali.enums.leave;

/**
 * Created by vali on 15-8-13.
 */
public enum LeaveTypeEnum {

    ALL(0,"全部",200,"所有假期类型"),

    LactationLeave(1, "哺乳假",200,
                   "中国政府规定在孩子未满 1周岁之前,女性员工每天享有 1小时的哺乳假。对于家在公司的刚生育完孩子的女性可按规定享有次假。但由于很少有员工住在公司附近能享有此假,公司决定给予凡无法享有此假的女性员工额外 5天的产假来替代此哺乳假。 \n"
                   + "\n"
                   + "China’s employment regulations provide for female employees to take one hour’s lactation leave each day until the child is 1 year old. Female employees who have given birth to a child, and who live close enough to their work can claim this entitlement. But since very few Rolls-Royce employees do live close enough to work to make this feasible, Rolls-Royce offers female employees an additional five days maternity leave instead of lactation leave.\n"
                   + "\n"
                   + "1小时的哺乳假将不可用现金补偿\n"
                   + "\n"
                   + "The 1-hour lactation leave can not be compensated by cash.\n"
    ),

    PreMaternityLeavePayDeduction(2, "产前假",200,
                                  "中国政府规定在孩子未满 1周岁之前,女性员工每天享有 1小时的哺乳假。对于家在公司的刚生育完孩子的女性可按规定享有次假。但由于很少有员工住在公司附近能享有此假,公司决定给予凡无法享有此假的女性员工额外 5天的产假来替代此哺乳假。 \n"
                                  + "\n"
                                  + "China’s employment regulations provide for female employees to take one hour’s lactation leave each day until the child is 1 year old. Female employees who have given birth to a child, and who live close enough to their work can claim this entitlement. But since very few Rolls-Royce employees do live close enough to work to make this feasible, Rolls-Royce offers female employees an additional five days maternity leave instead of lactation leave.\n"
                                  + "\n"
                                  + "1小时的哺乳假将不可用现金补偿\n"
                                  + "\n"
                                  + "The 1-hour lactation leave can not be compensated by cash.\n"
                                  + " \n"
                                  + " \n"
                                  + "产假(Maternity Leave)\n"
                                  + "产前假(Pre-maternity Leave-Pay Deduction)\n"
                                  + "\n"
                                  + "产前假(Pre-maternity Leave)\n"
                                  + "\n"
                                  + "您需要提供医生/医院开具的产前病假证明。\n"
                                  + "\n"
                                  + "You will be required to provide a medical certificate issued by a hospital or doctor in order to be entitled to this leave during your pregnancy. \n"
                                  + "\n"
                                  + "上海:女职工妊娠7个月以上(按28周计算),每天可工间休息1小时。如工作许可,经本人申请,单位批准,可请产前假两个半月。\n"
                                  + "\n"
                                  + "Shanghai: Employees that are at least seven months (28 weeks) pregnant are entitled to a break of one hour every day. If work permits, employees will be entitled to two and a half months pre--maternity leave upon the approval of the company. \n"
                                  + "\n"
                                  + "大连:女职工怀孕7个月以上(含7个月),每天可工间休息1小时。如上班确有困难,经本人申请,单位批准,可按病假产前休息一、两个月。\n"
                                  + "\n"
                                  + "Dalian: Employees that are at least seven months (including  seven months) pregnant are entitled to a break of one hour every day. Employees experiencing difficulty their work will be entitled to 1-2 months of sick leave upon the approval of the company.  \n"
    ),

    MaternityLeave(3, "产假", 200,"单胎顺产者,产假90天,其中产前15天,产后75天;\n"
                            + "\n"
                            + "Employees giving natural birth to a single child will be entitled to 90 days maternity leave, including 15 days prior to child birth and 75 days thereafter.\n"
                            + "\n"
                            + "难产者,增加产假15天;多胞生育者,每多生育一个婴儿,增加产假15天\n"
                            + "\n"
                            + "Employees experiencing difficult labor will be granted an additional 15 days of maternity leave; Employees giving multiple births will be entitled to an additional 15 days of maternity leave for each extra child.\n"
                            + "\n"
                            + "上海:符合规定生育的晚育妇女(已婚妇女年满24周岁初育的为晚育),除享受国家规定的产假外增加晚育假30天,遇法定节假日顺延;\n"
                            + "\n"
                            + "Shanghai: Married women over 24 years of age giving birth to their first child (children) in accordance with the relevant stipulations will be granted an extra 30 days of maternity leave (exclusive of public holidays) in addition to the maternity leave stipulated by the government. \n"
                            + "\n"
                            + "大连:晚育并领取«独生子女父母光荣证»的,产假增加60日。\n"
                            + "\n"
                            + "Dalian: For married women over 24 years of age giving birth to a child (children), and that have a “Parents of a Single Child’ Certificate, an additional 60 days of maternity leave will be granted. \n"),

    PregnancyCheckUpsLeave(4, "孕期检查假", 200,"本公司遵守怀孕检查的法定要求对于上述检查预约,您有权享有带薪休假,但需提供相关证明。您应将您的怀孕检查计划提前通知您的直线经理\n"
                                       + "\n"
                                       + "The Company complies with statutory requirements on pregnancy check-ups you will be entitled to paid leave for such appointments by providing valid certificate of doctor. You should inform your manager the check-up plan in advance.\n"
                                       + "\n"),

    SickLeave(5, "带薪病假",10, "每年您有权享受为期 10天的带薪病假。带薪病假天数不能结转至下一年。\n"
                         + "\n"
                         + "You are entitled up to 10 paid sick leave days each calendar year. This leave cannot be carried forward into the following year.\n"
                         + "\n"
                         + "您必须在返回工作当天提供医生/医院开具的病假证明,针对看病当天部份医生/医院无法出具病假证明的,您必须提供当天看病的病历卡复印件。\n"
                         + "\n"
                         + "On the same day that you return to work you must complete a Leave Application form and provide your HR or Department PA / Secretary /Office Administrator with a copy of your Medical certificate or Medical Record\n"
                         + "\n"
                         + "本公司有权指定特定医院开具诊断书。 \n"
                         + "\n"
                         + "The Company has the right to assign a specific hospital for the medical certificate.\n"),

    MarriageLeave(6, "婚假",200, "您首次合法结婚可按国家规定享受3天婚假,晚婚的员工可增加晚婚假7天,晚婚假应当与婚假合并连续使用,遇法定节假日顺延。(女性年满23周岁,男性年满25周岁初婚的为晚婚)。\n"
                           + "\n"
                           + "As is stipulated in national regulations, you will be entitled to 3 working days of paid leave for your first lawful marriage. Employees who marry late will be entitled to an additional seven days of marriage leave. Additional marriage leave must be taken at the same time as the standard 3 working days of leave. Should there be a public holiday during the period of marriage leave, the period of leave shall be extended accordingly. (The term “Marry Late” refers to females over the age of 23 or males over the age of 25 that are marrying for the first time).\n"
                           + "\n"
                           + "为获准婚假,您需要提供结婚证复印件。 \n"
                           + "\n"
                           + "Employees wishing to take marriage leave will be required to provide a photocopy of their marriage certificate.\n"
                           + "\n"),

    PaternityLeave(7, "看护假",200, "所有父亲在有第一个孩子时都有权享有看护假。看护假为期 3天。\n"
                             + "All fathers are entitled paternity leave for their first child. The length of paternity leave that you are entitled to is 3 days.\n"
                             + "\n"
                             + "对于大连晚育并领取«独生子女父母光荣证»的员工,看护假为15日。\n"
                             + "Fathers in Dalian are entitled 15 days of paternity leave if they present the Certificate of “Parents of Single Child”.\n"
                             + "\n"
                             + "您需要提供孩子的出生证明和独生子女证明,才能获得看护假。\n"
                             + "You will be required to complete the Leave Application form and provide a copy of your child’s birth certificate for your paternity leave to be granted.\n"),

    SickLeavePayDeduction(8, "扣薪病假",200,
                          "如果您申请扣薪病假(例如由于生病,您一年内休病假的时间超过 10天),您需要提供医生/医院开具的确认您无法工作的病假证明。本公司有权指定特定医院,并有权与您的医生/医院交流确认您无法工作。有关长期生病假期的规定将适用当地政府规定。 \n"
                          + "\n"
                          + "If you suffer pro-longed sickness (i.e. you are absent due to sickness beyond your 10 calendar day entitlement in any one year) you are required to provide a Medical certificate from your doctor / hospital to confirm your inability to work. The Company has the right to assign a specific hospital and is entitled to speak to your doctor / hospital to confirm your inability to work. The payment for this pro-longed sick leave will be applied as per local government regulations.\n"
    ),

    AnnualLeaveEntitlement(9, "带薪年假",200,
                           "所有办公室员工将每年享有 15天年假;工作满 5年后第一个年度一月一日起年假天数将每年增加 1天,但最多年假天数为 20天;每年最多有 5天年假可以延期至明年三月底之前使用。任何在第二年三月底前未及时修完的年假除特殊原因外都将作为自动放弃处理。\n"
                           + "\n"
                           + "All office employees are entitled to have 15 days annual leave per year. After finished 5 years service, from the first January 1st of the year, the annual leave entitlement for office employees will increase by 1 day for each additional year of service up to a maximum of 20 days. Maximum 5 days annual leave can be carried forward to next year before the end of March. Any leave days not taken by the end of March of the next year will be lost unless there are exceptional circumstances.\n"
                           + "\n"
                           + "年假最少使用天数\n"
                           + "您每次请年假最少以半天为基数。 \n"
                           + "\n"
                           + "Minimum Period of Annual Leave Application\n"
                           + "Half a day is the minimum base for you to apply for annual leave each time.\n"
                           + "\n"
                           + "在试用期内可享受的年假\n"
                           + "在试用期内,您只可以休最多2天年假。\n"
                           + "\n"
                           + "Annual Leave Entitlement during your Probation Period\n"
                           + "During your probation period, you are only entitled to a have maximum 2 days annual leave.\n"
                           + "\n"
                           + "新员工和离职人员可享受的年假按比例计算。 \n"
                           + "\n"
                           + "Annual leave entitlement for new starters and leavers is calculated on a pro-rata basis.\n"
    ),

    CompassionateLeave(10, "丧假",200, "如果您的直系亲属(父母、配偶、孩子)去世,您可享有 3天有薪假期。\n"
                                 + "如果您的旁系亲属(岳父母、祖父母、兄弟姐妹、配偶之祖父母)去世,您可享有 \n"
                                 + "1天有薪假期。\n"
                                 + "\n"
                                 + "If you suffer an immediate family bereavement (parents, spouse, children), you will be granted 3 days paid leave. If you suffer an extended family bereavement (parents-in-law, grandparents, siblings, grandparents-in-law), you will be granted 1 days paid leave. \n"
                                 + "\n"
                                 + "要休该等假期的员工应尽早通知其经理,您还需要提供一份死亡证明。 \n"
                                 + "\n"
                                 + "An employee wishing to take such leave should notify their Manager at the earliest opportunity and provide related certificate.\n"),

    NoPaidLeaveNoPay(11, "无薪事假",30, "在特殊情况下,员工可以申请无薪事假,但需经其直接经理批准。其直接经理有权决定是否批准无薪事假。无薪事假的申请应视具体情况而定。\n"
                                 + "\n"
                                 + "Under exceptional circumstances you may wish to take unpaid leave. It is up to your Manager’s discretion as to whether unpaid leave will be granted. \n"
                                 + "\n"
                                 + "所有员工可申请的无薪事假,最长时间为 1个月(或最多一年内累计达 30天)。\n"
                                 + "\n"
                                 + "Applications for unpaid leave will be dealt with on a case by case basis. The maximum unpaid leave is 1 month (or a maximum cumulative total of 30 days within 1 year).\n"
                                 + "\n"
                                 + "如果使用的无薪事假超过 30天,公司有权终止雇用合同。\n"
                                 + "\n"
                                 + "If you exceed 1 month unpaid leave, the Company has the right to terminate your employment.\n"
                                 + " \n"),

    MiscarriageLeave(12, "自然流产假",200, "上海:妊娠3个月内自然流产或子宫外孕者,给予产假30天;妊娠3个月以上,7个月以下自然流产者,给予产假45天。您需要提供医生/医院开具的流产证明。\n"
                                  + "\n"
                                  + "Shanghai: Employees suffering a miscarriage or ectopic pregnancy during the first three months of their pregnancies (first trimester) will be entitled to 30 days of maternity leave. Employees suffering a miscarriage from the fourth to the seventh month of their pregnancies (second trimester) will be entitled to 45 days of maternity leave.\n"
                                  + "\n"
                                  + "A miscarriage certificate issued by a hospital or a doctor will be required.\n"
                                  + "\n"
                                  + "大连:女职工自然流产应凭县、区以上医疗保健单位的证明休假。其中,怀孕不满4个月流产的,休假15天至30天;怀孕满4个月以上(含4个月)至7个月以下流产的,休假42天。\n"
                                  + "\n"
                                  + "Dalian: Employees suffering a miscarriage or ectopic pregnancy during the first four months of their pregnancies are entitled 15-30 days of maternity leave. Employees suffering a miscarriage from the fifth month to the seventh month (including 4 months exactly) of their pregnancies are entitled 42 days of maternity leave. \n"
                                  + "\n"
                                  + "A miscarriage certificate issued by a local county (district) healthcare provider will be required.\n"
                                  + "\n");

    private int type;

    private String name;

    private String desc;

    private int defaultDayNum;

    public int getType() {
        return type;
    }


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getDefaultDayNum(){return defaultDayNum;}

    LeaveTypeEnum(int type, String name,int defaultDayNum, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
        this.defaultDayNum = defaultDayNum;
    }

    public static LeaveTypeEnum getLeaveType(int type) {

        LeaveTypeEnum[] enums = LeaveTypeEnum.values();

        for (LeaveTypeEnum t : enums) {
            if (t.getType() == type) {
                return t;
            }
        }

        return null;
    }

}
