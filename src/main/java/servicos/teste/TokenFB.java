package servicos.teste;

public class TokenFB {
	// https://developers.facebook.com/tools/explorer?method=GET&path=1314662168
	
	private String Token = "CAACEdEose0cBANKjfZAv6gns7CgRSdZAWxBSZBEClqztN0W0boZAmJHLumGlzkFzfeFj60y8lbbsrLSvMICFUDAP49KKLWd18LMy1cbBxtmI9Drb2UjcZBzkahEnxed7FY6sWDdavzvJ24jOu5rT9TYtacxDNJKCciICQ8EEudY8Yk0VlOonAcngmj0Mzilh23e4neL0mKeEslJMcoaEN";

	public String getToken() {

		System.out.println("\nCaso n�o funcione lembre de ver se o Token n�o expirou!\n Veja tamb�m se o Token tem permiss�o de acessar os dados...");
		System.out.println("\n Para pegar ou atualizar o Token acesse:\nhttps://developers.facebook.com/tools/explorer?method=GET&path=1314662168");
		
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
