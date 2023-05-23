package sk.kryptotipy.web.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * denny requests from spammers
 */
@Slf4j
@Component
public class DenyIpAddressesInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

      var ip = getIp(request);
      var isBannedIp = denyIps().contains(ip);
      if (isBannedIp) {
          log.info("IP address {} is banned. Stopping processing the request.", ip);
      }
      return !isBannedIp;
    }


    private String getIp(HttpServletRequest request) {
        var ipAddr = request.getHeader("X-FORWARDED-FOR");
        return ipAddr == null ? getRemoteAddr(request) : ipAddr;
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }

    private List<String> denyIps() {
        return List.of(
            "37.57.218.243",
            "135.181.180.59",
            "34.141.25.41",
            "218.188.41.122",
            "95.91.75.79",
            "95.217.109.26",
            "34.141.25.41",
            "95.217.109.26",
            "135.181.213.220",
            "158.69.22.94",
            "192.99.15.33",
            "65.108.125.120",
            "164.132.201.7"
             // test "127.0.0.1"
        );
    }
}
