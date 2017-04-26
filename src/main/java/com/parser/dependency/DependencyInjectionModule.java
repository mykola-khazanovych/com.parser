package com.parser.dependency;

import com.google.inject.AbstractModule;
import com.parser.utils.*;


/**
 * @author Mykola Khazanovych
 *         23.04.2017;
 */
public class DependencyInjectionModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(OffersLinksListBuilder.class).to(OffersLinksListBuilderImpl.class);
        bind(OffersManager.class).to(OffersManagerImpl.class);
        bind(OutputHelper.class).to(OutputHelperImpl.class);
        bind(PageCrawler.class).to(PageCrawlerImpl.class);
    }
}
