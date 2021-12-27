package com.samsungclone.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import com.day.cq.wcm.api.Page;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
@Model(adaptables = {Resource.class,SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {

    @ScriptVariable
    private Page currentPage;
    @ValueMapValue
    private List<PageDetail> datafromModelList = new ArrayList<PageDetail>();
    @PostConstruct
    public void init() {
        try {

            if(currentPage!=null)
            {
                Iterator<Page> listChildPages = currentPage.listChildren();
                if (!listChildPages.hasNext()){
                    listChildPages = currentPage.getParent(2).listChildren();
                }
                while (listChildPages.hasNext()) {
                    Page childPage = listChildPages.next();
                    PageDetail detail = new PageDetail();
                    detail.setTitle(childPage.getTitle());
                    detail.setDescription(childPage.getDescription());
                    datafromModelList.add(detail);
                }
            }

        }
        catch(Exception e) { e.printStackTrace();}
    }
    public List<PageDetail> getDatafromModelList() {
        return datafromModelList;
    }

    public Page getCurrentPage() {
        return currentPage;
    }
}