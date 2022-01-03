echo $(date)": Starting read news"
export MYSQL_PWD="Coins123456#"
curl http://194.135.81.84:8081/sql/news >> /home/post_extractor/extracted_data.sql
mysql -u root kryptotipy_db < /home/post_extractor/extracted_data.sql
cat /home/post_extractor/extracted_data.sql >> /home/post_extractor/archived_data.sql
yes | cp /dev/null /home/post_extractor/extracted_data.sql
echo $(date)": Ending read news"