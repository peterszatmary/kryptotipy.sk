package sk.kryptotipy.web.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sk.kryptotipy.web.AppProperties;
import sk.kryptotipy.web.data.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@Data
@Component
public class ArticleComponent {
    private final AppProperties appProperties;
    private final Map<String, String> postsMeta;
    private final List<Article> articles;

    private void addAllPostsMeta() {
        var baseUrlWithoutPort = appProperties.getBaseUrlWithoutPort();
        postsMeta.put("post.image.all",           baseUrlWithoutPort + "assets/post-images/bitcoin/images_2.jpg");
        postsMeta.put("post.image.bitcoin",       baseUrlWithoutPort + "assets/post-images/bitcoin/images_3.jpg");
        postsMeta.put("post.image.litecoin",      baseUrlWithoutPort + "assets/post-images/litecoin/images_6.jpg");
        postsMeta.put("post.image.monero",        baseUrlWithoutPort + "assets/post-images/monero/images_3.jpg");
        postsMeta.put("post.image.tezos",         baseUrlWithoutPort + "assets/post-images/tezos/images_8.jpg");
        postsMeta.put("post.image.cosmos",        baseUrlWithoutPort + "assets/post-images/cosmos/images_8.jpg");
        postsMeta.put("post.image.polkadot",      baseUrlWithoutPort + "assets/post-images/polkadot/images_2.jpg");
        postsMeta.put("post.image.algorand",      baseUrlWithoutPort + "assets/post-images/algorand/images_9.jpg");
        postsMeta.put("post.image.kryptomeny",    baseUrlWithoutPort + "assets/post-images/kryptomeny/images_1.jpg");
        postsMeta.put("post.image.blockchain",    baseUrlWithoutPort + "assets/post-images/blockchain/images_2.jpg");
        postsMeta.put("post.image.binance",       baseUrlWithoutPort + "assets/post-images/binance/images_10.jpg");
        postsMeta.put("post.image.bitfinex",      baseUrlWithoutPort + "assets/post-images/bitfinex/images_7.jpg");
        postsMeta.put("post.image.bittrex",       baseUrlWithoutPort + "assets/post-images/bittrex/images_3.jpg");
        postsMeta.put("post.image.dag",           baseUrlWithoutPort + "assets/post-images/dag/images_4.jpg");
        postsMeta.put("post.image.ecb",           baseUrlWithoutPort + "assets/post-images/ecb/images_2.jpg");
        postsMeta.put("post.image.fed",           baseUrlWithoutPort + "assets/post-images/fed/images_2.jpg");
        postsMeta.put("post.image.dash",          baseUrlWithoutPort + "assets/post-images/dash/images_1.jpg");
        postsMeta.put("post.image.exodus",        baseUrlWithoutPort + "assets/post-images/exodus/images_6.jpg");
        postsMeta.put("post.image.neo",           baseUrlWithoutPort + "assets/post-images/neo/images_7.jpg");
        postsMeta.put("post.image.ontology",      baseUrlWithoutPort + "assets/post-images/ontology/images_1.jpg");
        postsMeta.put("post.image.ripple",        baseUrlWithoutPort + "assets/post-images/ripple/images_9.jpg");
        postsMeta.put("post.image.vechain",       baseUrlWithoutPort + "assets/post-images/vechain/images_4.jpg");
        postsMeta.put("post.image.kraken",        baseUrlWithoutPort + "assets/post-images/kraken/images_4.jpg");
        postsMeta.put("post.image.ethereum",      baseUrlWithoutPort + "assets/post-images/ethereum/images_4.jpg");
        postsMeta.put("post.image.halving",       baseUrlWithoutPort + "assets/post-images/halving/images_1.jpg");
        postsMeta.put("post.image.fork",          baseUrlWithoutPort + "assets/post-images/fork/images_3.jpg");
        postsMeta.put("post.image.ico",           baseUrlWithoutPort + "assets/post-images/ico/images_1.jpg");
        postsMeta.put("post.image.coinbase",      baseUrlWithoutPort + "assets/post-images/coinbase/images_1.jpg");
        postsMeta.put("post.image.coinmarketcap", baseUrlWithoutPort + "assets/post-images/coinmarketcap/images_4.jpg");
        postsMeta.put("post.image.burza",         baseUrlWithoutPort + "assets/post-images/burza/images_3.jpg");
        postsMeta.put("post.image.cardano",       baseUrlWithoutPort + "assets/post-images/cardano/images_1.jpg");
        postsMeta.put("post.image.nbs",           baseUrlWithoutPort + "assets/post-images/nbs/images_10.jpg");
        postsMeta.put("post.image.wallets",       baseUrlWithoutPort + "assets/post-images/peňaženky/images_2.jpg");
        postsMeta.put("post.image.altcoin",       baseUrlWithoutPort + "assets/post-images/altocin/images_10.jpg");
        postsMeta.put("post.image.nft",           baseUrlWithoutPort + "assets/post-images/nft/images_1.jpg");
    }

    private void addPost(
            String postKey,
            String postTitle,
            String postDescription,
            String postImagePathExtension,
            String imgWidth,
            String imgHeight,
            LocalDateTime postDate
    ) {
        postsMeta.put("post.title." + postKey, postTitle);
        postsMeta.put("post.description." + postKey, postDescription);
        postsMeta.put("post.image." + postKey,
                this.appProperties.getBaseUrlWithoutPort() +
                        "assets/img/articles/index/" +
                        postKey +
                        postImagePathExtension);
        postsMeta.put("post.image.width." + postKey, imgWidth);
        postsMeta.put("post.image.height." + postKey, imgHeight);
        postsMeta.put("post.date." + postKey,  postDate + "");
    }

    public void addPostsAndArticlesMeta() {
        articles.stream()
                .forEach((var x) -> addPost(
                        x.getPostMeta().metaPostKey(),
                        x.getPostMeta().metaPostTitle(),
                        x.getPostMeta().metaPostDescription(),
                        x.getPostMeta().metaPostImageExtension(),
                        x.getPostMeta().metaImgWidth(),
                        x.getPostMeta().metaImgHeight(),
                        x.getPostMeta().metaPostDate()
                        ));
        addAllPostsMeta();
    }

    public String getPostTitle(String key) {
        return postsMeta.get("post.title." + key);
    }

    public String getPostDescription(String key) {
        return postsMeta.get("post.description." + key);
    }

    public String getPostImage(String key) {
        return postsMeta.get("post.image." + key);
    }

    public String getPostDate(String key) {
        return postsMeta.get("post.date." + key);
    }

    public String getPostImageWidth(String key) {
        return postsMeta.get("post.image.width." + key);
    }

    public String getPostImageHeight(String key) {
        return postsMeta.get("post.image.height." + key);
    }
}