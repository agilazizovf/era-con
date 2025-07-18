-- Insert into users
INSERT INTO users (email, password) VALUES
                                        ('admin@gmail.com', '$2a$12$RTNq7JGW68FJDTsc3bGxmeyQueGFqAOh5NByss/JyvCglxKYZQ6zi');

-- Insert roles
INSERT INTO roles(name, admin)
VALUES
    ('ROLE_ADMIN', 1);

-- Assign roles automatically based on user type
INSERT INTO user_roles (user_id, role_id)
select 1,id from roles where admin=1;

-- insert into abouts(title, description)
-- values ('Şirkət haqqında', 'Şirkətimiz 2019-ci ildən öz fəaliyyətinə başlamış ' ||
--                            'bugünə qədər bir çox layihələrə imza atıb. Bizlər bu gün “ERACON” ' ||
--                            'olaraq qarşımıza qoyduğumuz məqsədlərə artıq böyük ölçüdə nail olmuşuq. ' ||
--                            'Hər zaman müştərilərin istək və arzularını həyata keçirmək, eyni zamanda ' ||
--                            'müştəri məmnuniyyətini yüksək tutmaq, yüksək keyfiyyətdə xidmət göstərmək ' ||
--                            'şirkətin əsas prioritetlərindən biridir.');
--
-- insert into about_pictures(file_name, about_id)
-- values ('file1.png', 1),
--     ('file2.png', 1),
--     ('file3.png', 1);
--
--
-- INSERT INTO license_advices (title)
-- VALUES ('ERACON olaraq müştərilərimizə lisenziya və icazələrin alınması üzrə məsləhət xidməti həyata keçiririk.');
--
-- INSERT INTO license_advice_descriptions (license_advice_id, descriptions)
-- VALUES
--     (1, 'Tikintisinə icazə tələb olunan bina və qurğuların mühəndis-axtarış işləri.'),
--     (1, 'Tikintisinə icazə tələb olunan bina və qurğuların tikinti-quraşdırma işləri.'),
--     (1, 'Tikintisinə icazə tələb olunan və barəsində məlumatlandırma icraatı tətbiq olunan bina və qurğuların layihələndirilməsi.'),
--     (1, 'Təhlükəli yüklərin nəqliyyat vasitəsilə daşınması.'),
--     (1, 'Maye və təbii qaz təsərrüfatı obyektlərinin quraşdırılması və istismarı Maye və təbii qaz təsərrüfatı obyektlərinin quraşdırılması.'),
--     (1, 'Maye və təbii qaz təsərrüfatı obyektlərinin istismarı.'),
--     (1, 'Dağ-mədən işləri, dağ və buruq qazmalarının aparılması.'),
--     (1, 'Liftlərin quraşdırılması və təmiri fəaliyyəti Attraksionların istismarı .'),
--     (1, 'Qaldırıcı qurğuların, metallurgiya avadanlığının, təzyiq altında işləyən qazanların, tutumların quraşdırılması və təmiri.'),
--     (1, 'Əczaçılıq fəaliyyəti.'),
--     (1, 'Yanğından mühafizə sistemlərinin və vasitələrinin quraşdırılması, texniki xidməti və təmiri.'),
--     (1, 'Dövlət məktəbəqədər təhsil müəssisələri istisna olmaqla, məktəbəqədər təhsil müəssisələri.'),
--     (1, 'Peşə təhsili müəssisələri.'),
--     (1, 'Əlavə təhsil müəssisələri.');
--
-- INSERT INTO consulting_on_project_works (title)
-- VALUES ('ERACON olaraq müştərilərimizə layihə işlərinin hazırlanması, sənədləşməsi və adiyyəti üzrə məsləhət xidmətini göstərir.');
--
-- INSERT INTO consulting_on_project_work_descriptions (consulting_on_project_work_id, descriptions)
-- VALUES
--     (1, 'Memarlıq işçi və eskiz layihələrin hazırlanması.'),
--     (1, 'Konstruksiya işçi layihələrinin və hesablarının hazırlanması.'),
--     (1, 'Mühəndis kommunikasiya layihələrinin hazırlanması.'),
--     (1, 'Şəhərsalma əsaslandırmamlayihələrinin hazırlanması.'),
--     (1, 'Ekoloji layihələrin hazırlanması və pasportların alınması.'),
--     (1, 'Fərdi yaşayış evlərinin şəhərsalma və icra orqanlarında məlumatlandırma icraasının təşkil edilməsi');
--
-- insert into repair_constructions(title)
-- values ('Şirkətimiz yaşayış və qeyri yaşayış binalarının tikintisini və təmirini həyata keçirir.');
--
-- insert into repair_construction_descriptions(repair_construction_id, descriptions)
-- values (1, 'd1');
--
-- insert into construction_audits(title)
-- values ('ERACON şirkəti olaraq tikinti auditinin təşkilini həyata keçiririk.');
--
-- insert into construction_audit_descriptions(construction_audit_id, descriptions)
-- values (1, '1. Layihələrin araşdırılması (irad və təkliflərin verilməsi)
--       - Dəmir beton konstruksiyalı bina və qurğular
--       - Metal konstruksiyalı bina və qurğular'),
--     (1, '2. İş həcmlərinin verilmiş layihəyə əsasən hazırlanması'),
--     (1, '3. İş həcmlərinin dəyərləndirilməsi (cari qiymətlərlə ilkin smetaların tutulması)'),
--     (1, '4. Layihə üzrə işlərin təşkili (iş planı və qrafiklərin yazılması)'),
--     (1, '5. Layihə üzrə işlərə nəzarət'),
--     (1, '6. Texniki aktların hazırlanması
--        - Özülaltı açıq xəndəyə baxış aktı
--        - Oxlar üzrə bina və qurğuların yerinə (naturaya) köçürmə aktı
--        - Bünovrə altı hazırlıq qatının qurulması aktı
--        - Sonradan üstü örtülən işlərin (gizli akt), məsul konstruksiyaların qəbul edilmə aktı
--        - Görülmüş işlərin qəbul aktı'),
--     (1, '7. Tikinti sahəsinə aid jurnalların hazırlanması və nəzarəti
--        - Ümumi işlər jurnalı
--        - Beton işləri jurnalı
--        - Qaynaq işləri jurnalı
--        - SƏTƏM jurnalları'),
--     (1, '8. Layihələrə texniki nəzarət işləri
--        - Konstruksiya nəzarəti (Beton işlərindən qabaq qəlib və armaturun layihəyə uyğunluğunun yoxlanılması
--        - Memarlıq (Layihədən kənara çıxmaların yoxlanılması və aktlaşdırılması)
--        - Mühəndis kommunikasiya layihələrinin yerində yoxlanılması (Havalandırma,Su və kanalizasiya, İstilik – ventilyasiya,Elektrik işləri, Siqnalizasiya və yanğın təhlükəsizlik, Zəif axın rabitə, video müşahidə və s.)'),
--     (1, '9. Bina və qurğuların dövlət qurumları ilə təhvil təslim aktlarının hazırlanması'),
--     (1, '10. Tikinti və layihə sənədlərinin, smetalarının auditi
--        - Tikintiyə təqdim olunmuş cizgilərin ( istehsalat və quraşdırma çizgilərin ) auditi
--        - Layihə smeta sənədləri ilə tikinti zamanı tutulan foma2 arayışlarının auditi
--        - Tender (açıq və qapalı) və katirovka sorğularının SMF (Satınalma müraciət forması) və TTF (Texniki tələbnamə forması) əsasən auditi');
--
--
-- insert into purchases(title)
-- values ('Satınalma prosesi müddətində sənədlərin hazırlanması və sistemə işlənməsi.');
--
-- insert into purchase_descriptions(purchase_id, descriptions)
-- values (1, 'This is description1');
--
-- INSERT INTO projects (
--     location,
--     title,
--     area,
--     start_date,
--     end_date,
--     main_image
-- ) VALUES (
--              'Ramana Qəsəbəsi',
--              'Ramana - 2 mərtəbəli fərdi yaşayış evinin tikintisi',
--              350,
--              '2022-07-16',
--              NULL,
--              'main_image_1.jpg'
--          ),
--          (
--              'ŞUŞA 110/35/10 KV Yarımstansiyası',
--              'Fasadın kermoqranitlə üzlənməsi',
--              0,
--              '2021-01-02',
--              '2021-10-03',
--              'main_image_2.jpg'
--          );
--
--
-- insert into partners(picture_url, web_site_url)
-- values ('picture1.png', 'https://www.site1.com/'),
--        ('picture2.png', 'https://www.site2.com/'),
--        ('picture3.png', 'https://www.site3.com/');