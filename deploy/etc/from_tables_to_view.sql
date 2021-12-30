INSERT INTO post_with_label(post_id, post_url, post_title, post_source, post_birthday, post_source_image, post_original_image, post_original_image, post_source_render, post_lang, post_clicks, post_likes, post_disabled, post_external, label_name, label_relevance)

-- teraz ten najspravnejsi
SELECT p.id as post_id, p.url as post_url, p.title as post_title, p.source as post_source, p.birthday as post_birthday, p.img_src as post_source_image, p.original_img_src as post_original_image, p.source_render as post_source_render, p.i18n as post_lang, p.clicks as post_clicks, p.likes as post_likes, p.external as post_external, p.disabled as post_disabled, l.name as label_name, pi.count as label_relevance
FROM post as p JOIN post_index as pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id
WHERE p.img_src is not null and
pi.count=(select max(u.count) from post_index as u where u.post_id = p.id)
ORDER BY post_url and post_birthday desc;

SELECT count(*) FROM post as p join label as l join post_index as pi on p.id = pi.post_id and l.id = pi.label_id;

-- teraz vsetky
SELECT count(*),p.id as post_id, p.url as post_url, p.title as post_title, p.source as post_source, p.birthday as post_birthday, p.img_src as post_source_image, p.original_img_src as post_original_image, p.source_render as post_source_render, p.i18n as post_lang, p.clicks as post_clicks, p.likes as post_likes, p.external as post_external, p.disabled as post_disabled, l.name as label_name, pi.count as label_relevance FROM post as p JOIN post_index as pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id WHERE p.img_src is not null  ORDER BY post_url and post_birthday desc;


-- TODO potrebujem 4 z kazdeho zoradene podla max
SELECT p.id as post_id, p.url as post_url, p.title as post_title, p.source as post_source, p.birthday as post_birthday, p.img_src as post_source_image, p.original_img_src as post_original_image, p.source_render as post_source_render, p.i18n as post_lang, p.clicks as post_clicks, p.likes as post_likes, p.external as post_external, p.disabled as post_disabled, l.name as label_name, pi.count as label_relevance
FROM post as p JOIN post_index as pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id
WHERE p.img_src is not null and pi.count in
(select u.count from post_index as u where u.post_id = p.id order by u.count desc limit 4)
ORDER BY post_url and post_birthday desc;

SELECT p.id as post_id, p.url as post_url, p.title as post_title, p.source as post_source, p.birthday as post_birthday, p.img_src as post_source_image, p.original_img_src as post_original_image, p.source_render as post_source_render, p.i18n as post_lang, p.clicks as post_clicks, p.likes as post_likes, p.external as post_external, p.disabled as post_disabled, l.name as label_name, pi.count as label_relevance,
FROM (
row_number () over (partition by post_url
ORDER BY post_url and post_birthday desc
) rn
) post as p JOIN post_index as pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id
WHERE p.img_src is not null and
 rn>3 ORDER BY post_url and post_birthday desc;




SELECT p.id as post_id, p.url as post_url, p.title as post_title, p.source as post_source, p.birthday as post_birthday, p.img_src as post_source_image, p.original_img_src as post_original_image, p.source_render as post_source_render, p.i18n as post_lang, p.clicks as post_clicks, p.likes as post_likes, p.external as post_external, p.disabled as post_disabled, l.name as label_name, pi.count as label_relevance FROM (select row_number() over (partition by post_url ORDER BY post_url and post_birthday desc) rn from post) and post as p JOIN post_index as pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id WHERE p.img_src is not null and rn>3 ORDER BY post_url and post_birthday desc;
-- raw
row_number () over (
         partition by customer_id
         order by order_datetime desc
       ) rn
