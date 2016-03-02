package com.pranveraapp.common.web.resource;

import com.pranveraapp.common.classloader.release.ThreadLocalManager;
import com.pranveraapp.common.web.PranveraAppRequestContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by elion on 04/02/16.
 */
@Service("elPranveraAppContextUtil")
public class PranveraAppContextUtil {


    public void establishThinRequestContext() {
        establishThinRequestContextInternal(true, true);
    }

    protected void establishThinRequestContextInternal(boolean includeTheme, boolean includeSandBox) {
        PranveraAppRequestContext prc = PranveraAppRequestContext.getPranveraAppRequestContext();

        if (prc.getRequest() == null) {
            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = req.getSession(false);
            //SecurityContext ctx = readSecurityContextFromSession(session);
            //if (ctx != null) {
            //    SecurityContextHolder.setContext(ctx);
            //}
            prc.setRequest(req);
        }

        /**WebRequest wr = brc.getWebRequest();

        if (brc.getNonPersistentSite() == null) {
            brc.setNonPersistentSite(siteResolver.resolveSite(wr, true));
            brc.setSandBox(sbResolver.resolveSandBox(wr, brc.getNonPersistentSite()));
            brc.setDeployBehavior(deployBehaviorUtil.isProductionSandBoxMode() ? DeployBehavior.CLONE_PARENT : DeployBehavior.OVERWRITE_PARENT);
        }

        if (includeTheme) {
            if (brc.getTheme() == null) {
                brc.setTheme(themeResolver.resolveTheme(wr));
            }
        }*/
    }


    public void clearThinRequestContext() {
        ThreadLocalManager.remove();
    }
}
