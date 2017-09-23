package br.jus.cjf.simus.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.jus.cjf.simus.model.Usuario;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetailsSource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@Service
public class SimusAuthenticationProvider implements
		AuthenticationProvider, UserDetailsService {

	@Autowired
	private SimusService simusService;

	// Isso mesmo, string vazia é o retorno ok
	// "Inacreditável"
	public static final String RETORNO_OK = "";
        
        
        private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
        } 
 
        public static String SHA1(String text) 
            throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            sha1hash = md.digest();
            return convertToHex(sha1hash);
        } 
        

	/**
	 * Valida o usuário informado no webservice de login do SIMUS.
	 * 
	 * @param usuario
	 * @return boolean indicando se a senha/usuario conferirem.
	 */
	public boolean autentica(UsernamePasswordAuthenticationToken auth) {
		try {
			
               //CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) auth.getDetails());
               //String contrato = webAuthenticationDetails.getnContrato();
                /*        ILogin iLogin = new ILoginserviceLocator().getILoginPort();
			return RETORNO_OK.equals(iLogin.pLoginOracle(
					(String) auth.getPrincipal(),
					(String) auth.getCredentials(), ""));
                    */
                    return simusService.validaLogin((String) auth.getPrincipal(), SHA1((String) auth.getCredentials()));
                    
		//} catch (ServiceException e) { // nao da pra fazer nada, erro de infra
		//	throw new AutenticadorException(e);
		//} catch (RemoteException e) { // idem, erro de infra
		//	throw new AutenticadorException(e);
		//}
                }catch(Exception ex){
                    ex.printStackTrace();
                    return false;
                }
	}

	class AutenticadorException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AutenticadorException() {
			super();
		}

		public AutenticadorException(String message, Throwable cause) {
			super(message, cause);
		}

		public AutenticadorException(String message) {
			super(message);
		}

		public AutenticadorException(Throwable cause) {
			super(cause);
		}

	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		
		if (//((String) auth.getPrincipal()).matches("\\d+") && 
                        autentica(auth)) {
			Usuario usuario = simusService.buscaPorMatricula((String) auth.getPrincipal());
			return new UsernamePasswordAuthenticationToken(usuario.getMatricula(),
					null, usuario.getAuthorities());

		} else {
			throw new AuthenticationCredentialsNotFoundException(
					"No credentials found in context");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public UserDetails loadUserByUsername(String matricula)
			throws UsernameNotFoundException, DataAccessException {
		Usuario usuario = simusService.buscaPorMatricula(matricula);

		if (usuario == null) {
			throw new UsernameNotFoundException("Matrícula ou Senha Inválida");
		}
		return usuario;
	}

}
