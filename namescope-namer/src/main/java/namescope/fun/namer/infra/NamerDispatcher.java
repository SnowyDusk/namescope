package namescope.fun.namer.infra;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import namescope.fun.namer.claim.NameClaim;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.enums.NamerEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class NamerDispatcher implements ApplicationContextAware {

    private static final Map<Integer, Nameable> NAMER_CONTEXT = new ConcurrentHashMap<>();

    private static final Map<Nameable, NameClaim> META_CLAIM = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String CLAIM_PATH = "classpath:prompt/";

    @PostConstruct
    public void initContext() {
        Map<String, Object> namers = applicationContext.getBeansWithAnnotation(Namer.class);
        for (NamerEnum namerEnum : NamerEnum.values()) {
            try {
                String namerType = namerEnum.getNamerType();
                Integer namerId = namerEnum.getNamerId();
                if (!namers.containsKey(namerType)) {
                    log.warn("[namer context init] namer {} unregistered, namerId: {}", namerType, namerId);
                    continue;
                }

                Nameable namer = (Nameable) namers.get(namerType);
                NAMER_CONTEXT.put(namerId, namer);
                log.info("[namer context init] namer {} registered, namerId: {}", namerType, namerId);

                File file = ResourceUtils.getFile(STR."\{CLAIM_PATH}\{namerEnum.getClaimName()}.json");
                NameClaim nameClaim = mapper.readValue(file, NameClaim.class);
                META_CLAIM.put(namer, nameClaim);
                log.info("[namer context init] meta nameClaim {} registered, namer: {}", namerEnum.getClaimName(), namerType);

            } catch (Exception e) {
                log.error("[namer context init] namer {} register error, namerId: {}", namerEnum.getNamerType(), namerEnum.getNamerId());
            }

        }
    }

    public void process(NameContext nameContext) {
        Nameable namer = getInstance(nameContext.getNameParam().getNamerId());
        if (Objects.isNull(namer)) {
            log.error("[namer dispatch] namerId {} is not found", nameContext.getNameParam().getNamerId());
        }

        NameClaim metaClaim = META_CLAIM.get(namer);
        if (Objects.isNull(metaClaim)) {
            log.error("[namer dispatch] namerClaim is not found, namerId: {}", nameContext.getNameParam().getNamerId());
        }

        NameClaim nameClaim = new NameClaim();
        BeanUtils.copyProperties(metaClaim, nameClaim);
        nameContext.setNameClaim(nameClaim);

        namer.name(nameContext);
    }

    public Nameable getInstance(Integer namerId) {
        return NAMER_CONTEXT.get(namerId);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
