package lily;

import com.danzi.pac.domain.HrResource;
import com.danzi.pac.domain.SapCalucate;
import com.danzi.pac.domain.Utils;
import com.danzi.pac.service.HrResourceServiceI;
import com.danzi.pac.utils.Write;
import mc_style.functions.soap.sap.document.sap_com.ZWEB_CALCULATEStub;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.ws.axis2.SimpleServiceStub;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.rmi.RemoteException;
import java.util.Date;


/**
 * Describe ：测试
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/12.
 */
public class Test {
    private ApplicationContext ac;
    private HrResourceServiceI hrResourceServiceI;

    //public HrResource hrResource;

    @org.junit.Test
    public void hash() {
        ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml",
                "spring-mybatis.xml" });
        hrResourceServiceI = (HrResourceServiceI) ac.getBean("hrResourceBean");
        HrResource hrResource=new HrResource();
        hrResource.setUsername("admin");
        hrResource.setNewpassword("zaq123");
        hrResource.setCreateTime(new Date());

        //hrResourceServiceI.insert(utils);
            //connection = dataSource.getConnection();
            //System.out.print(hrResourceServiceI.addHrResource(hrResource));
        try {
            System.out.print(hrResourceServiceI.updateHrResource(hrResource,1));
        }catch (Exception e){
            Write.println("aaaa");
        }



    }
    @org.junit.Test
    public void setAc(){
        try {
            //  使用RPC方式调用WebService
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            //  指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(
                    "http://localhost:8080/axis2/services/SimpleService");
            options.setTo(targetEPR);
            //  指定getGreeting方法的参数值
            Object[] opAddEntryArgs = new Object[] {"超人"};
            //  指定getGreeting方法返回值的数据类型的Class对象
            Class[] classes = new Class[] {String.class};
            //  指定要调用的getGreeting方法及WSDL文件的命名空间
            QName opAddEntry = new QName("http://ws.apache.org/axis2", "getGreeting");
            //  调用getGreeting方法并输出该方法的返回值
            System.out.println(serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0]);
            //  下面是调用getPrice方法的代码，这些代码与调用getGreeting方法的代码类似
            classes = new Class[] {int.class};
            opAddEntry = new QName("http://ws.apache.org/axis2", "getPrice");
            System.out.println(serviceClient.invokeBlocking(opAddEntry, new Object[]{}, classes)[0]);
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    @org.junit.Test
    public void  testService(){
        try {
            SimpleServiceStub stub = new SimpleServiceStub();
            SimpleServiceStub.GetGreeting gg = new SimpleServiceStub.GetGreeting();
            gg.setName("比尔");
            SimpleServiceStub.GetPrice jj = new SimpleServiceStub.GetPrice();
            System.out.println( stub.getGreeting(gg).get_return());
            System.out.println(stub.getPrice(jj).get_return());


        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void testSap(){
        try {
            ZWEB_CALCULATEStub zweb_calculateStub = new ZWEB_CALCULATEStub();
            //HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
            HttpTransportProperties.Authenticator authenticator = new HttpTransportProperties.Authenticator();
            authenticator.setUsername("lily");
            authenticator.setPassword("liamhacker");
//            authenticator.setHost("192.168.10.211");
//            authenticator.setDomain("erpdev1.danzi.cn");
//            authenticator.setPort(8041);
            zweb_calculateStub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE,authenticator);
            ZWEB_CALCULATEStub.ZCalculate gg= new ZWEB_CALCULATEStub.ZCalculate();
            ZWEB_CALCULATEStub.Char10 char10 = new ZWEB_CALCULATEStub.Char10();
            char10.setChar10("4700000100");
            gg.setIpEbeln(char10);
            ZWEB_CALCULATEStub.TableOfZpoinfo zpoinfo = new ZWEB_CALCULATEStub.TableOfZpoinfo();
            gg.setZpoInfo(zpoinfo);
           // ZWEB_CALCULATEStub.ZCalculate zCalculate = new ZWEB_CALCULATEStub.ZCalculate();

            ZWEB_CALCULATEStub.ZCalculateResponse zCalculateResponse = zweb_calculateStub.zCalculate(gg);
            //zweb_calculateStub.zCalculate(gg);

            for (int i = 0;i<zCalculateResponse.getZpoInfo().getItem().length;i++){
//                SapCalucate sapCalucate = new SapCalucate();
//                sapCalucate.setEbeln(zCalculateResponse.getZpoInfo().getItem()[i].getEbeln());
//                sapCalucate.setEbelp(zCalculateResponse.getZpoInfo().getItem()[i].getEbelp());
                Write.println(zCalculateResponse.getZpoInfo().getItem()[i].toString());
            }
        } catch (Exception axisFault) {
            axisFault.printStackTrace();
        }
    }
}
