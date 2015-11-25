package org.elasticsearch.index.analysis;


import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;


/**
 * @author kimchy (shay.banon)
 */
public class PinyinTokenFilterFactory extends AbstractTokenFilterFactory {
    private String first_letter = "none";
    private String padding_char = "";

    @Inject
    public PinyinTokenFilterFactory(Index index, @IndexSettings Settings indexSettings, Environment environment, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettings, name, settings);
        try {
            first_letter = settings.get("first_letter", "none");
            padding_char = settings.get("padding_char", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    @Inject public PinyinTokenFilterFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings) {
//        super(index, indexSettings, name, settings);
//        first_letter = settings.get("first_letter", "none");
//        padding_char = settings.get("padding_char", "");
//    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new PinyinTokenFilter(tokenStream, padding_char, first_letter);
    }
}