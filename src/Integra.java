package tcc.com.saber.tcc.integration;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import br.com.saber.tcc.business.CityController;
import br.com.saber.tcc.business.ClasssController;
import br.com.saber.tcc.business.ProfileController;
import br.com.saber.tcc.business.StudentAllocationController;
import br.com.saber.tcc.business.UserController;
import br.com.saber.tcc.entities.City;
import br.com.saber.tcc.entities.DBauxiliar;
import br.com.saber.tcc.entities.Profile;

import br.com.saber.tcc.entities.User;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Connection;

public class Integra {

	//
	
	private ProfileController profileController;
	private UserController userController;
	private StudentAllocationController studentAllocationController;
	private ClasssController classsController;
	private CityController cityController;
	
	
	//
	
	
	
	//TESTE
					public static void main(String[] args) throws SQLException {
						
						//para testes
						
						DBauxiliar db = new DBauxiliar();
						db.setInstanciaDB("db_moodle_30");
						db.setUrlServidor("jdbc:mysql://localhost");
						db.setUsr("root");
						db.setPwd("root");
						//
						String url = "localhost";
						
						ArrayList<JsonMoodle>usuarios = new ArrayList<JsonMoodle>();
						Integra i = new Integra();
						
						usuarios = i.conectaMoodle(db.getUrlServidor().concat("/").concat(db.getInstanciaDB()), db.getUsr(), db.getPwd());
						
						System.out.println("$>"+usuarios.size()+" usuários importados do moodle.");
						
						String chave = "ivonicechuves@bol.com.br";
						JsonMoodle usuper = i.retornaUM(usuarios, chave);
						User user = i.jsonMoodToPersist(usuper);
						
						
						
						
						
						
					}
	//FIM TESTE
	
	//conexão com o redis
	public Jedis conectaJedis(String url){
		Jedis jedis = new Jedis(url);
		
		
		return jedis;
		
	};
	private void listarMailUsr() {
		// TODO Auto-generated method stub
		
		Set<String>users = conectaJedis("localhost").keys("*");
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
	}
	//listar Usuários do staging
	private ArrayList<JsonMoodle> listarUsuariosRedis(String url) {
		// TODO Auto-generated method stub

		Set<String>chaves = conectaJedis(url).keys("*");
		ArrayList<JsonMoodle>usuarios = new ArrayList<JsonMoodle>();
	System.out.println("começou");
		for (Iterator iterator = chaves.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			JsonMoodle e = new JsonMoodle();
			e.setName(conectaJedis(url).hget(string, "nome"));;
			e.setPhone(conectaJedis(url).hget(string, "telefone"));;
			e.setLastname(conectaJedis(url).hget(string, "lastname"));;
			e.setCpf(conectaJedis(url).hget(string, "cpf"));
			e.setDtype(conectaJedis(url).hget(string, "dtype"));;
			e.setCity_id(Integer.parseInt(conectaJedis(url).hget(string, "city_id")));;
			e.setProfile_id(Integer.parseInt(conectaJedis(url).hget(string, "Profile_id")));
			e.setSelectedTo(Boolean.parseBoolean(conectaJedis(url).hget(string, "selectedTo")));
			usuarios.add(e);
		}
		
		System.out.println("Fim do método!");
		return usuarios;
	}
	
	//pesquisa usuários pelo e-mail da base vinda do moodle
	
	private JsonMoodle findUsrByMail(ArrayList<JsonMoodle> users,String mail) {
		// TODO Auto-generated method stub
		System.out.println("Gezonel se disfarça!");
		JsonMoodle json = new JsonMoodle();		
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			JsonMoodle jsonMoodle = (JsonMoodle) iterator.next();
			if (mail.equals(jsonMoodle.getEmail())) {
				json = jsonMoodle;
				System.out.println("Achou o gezonel disfarçado de "+json.getName());
				return json;
			}
			
		}
		
		
	return json;
	}
	
	
	
	//conexão com o moodle
	public ArrayList<JsonMoodle> conectaMoodle(String url, String usr, String pwd) throws SQLException{
		ArrayList<JsonMoodle> users= new ArrayList<JsonMoodle>();
		
		
			java.sql.Connection conexao = DriverManager.getConnection(url,usr,pwd);
			if (conexao!=null) {
				System.out.println("Conectado!");
					Statement stmt = conexao.createStatement();
					// sql de retrieving do banco de dados				
					String sql = "select 	mdl_user.username, mdl_user.firstname,mdl_user.lastname,mdl_user.email,mdl_user.phone1,mdl_user.phone2 from mdl_user";
					ResultSet result = stmt.executeQuery(sql);	
				while(result.next()){
					String usernome = result.getString(1);
					String nome = result.getString(2);
					String ultNome = result.getString(3);
					String email = result.getString(4);
					String phone = result.getString(5);
					JsonMoodle e = new JsonMoodle();
					e.setName(nome);
					e.setEmail(email);
					e.setPhone(phone);
					e.setLastname(ultNome);
					e.setCity_id(1);
					e.setCpf(null);
					e.setDtype("STUDENT");
					e.setProfile_id(3);
					e.setSelectedTo(false);
					users.add(e);
				}
					
				
				result.close();
				conexao.close();
				System.out.println("connection closed");
				System.out.println("2 ...2..2....Gezonel goes out from his cape");
				
				//PAREI AQUI Ó
			}
			return users;
				
		
	}
	//busca dos dados - messaging
	
	private void persistStaging(ArrayList<JsonMoodle>usuarios, String url) {
		// TODO Auto-generated method stub

		for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
			JsonMoodle jsonMoodle = (JsonMoodle) iterator.next();
			//
			
			this.conectaJedis(url).hset(jsonMoodle.getEmail(),"nome" ,jsonMoodle.getName());
			this.conectaJedis(url).hset(jsonMoodle.getEmail(),"telefone",jsonMoodle.getPhone());
			this.conectaJedis(url).hset(jsonMoodle.getEmail(),"lastname",jsonMoodle.getLastname());
			this.conectaJedis(url).hset(jsonMoodle.getEmail(),"phone",jsonMoodle.getPhone());
			this.conectaJedis(url).hset(jsonMoodle.getEmail(),"dtype",jsonMoodle.getDtype());
								if (jsonMoodle.getCpf()==null) {
									this.conectaJedis(url).hset(jsonMoodle.getEmail(), "cpf", "null");
								}else{
									
									this.conectaJedis(url).hset(jsonMoodle.getEmail(), "cpf", jsonMoodle.getCpf());
								}
			this.conectaJedis(url).hset(jsonMoodle.getEmail(),"city_id",Integer.toString(jsonMoodle.getCity_id()));
			this.conectaJedis(url).hset(jsonMoodle.getEmail(),"Profile_id", Integer.toString(jsonMoodle.getProfile_id()));	
			this.conectaJedis(url).hset(jsonMoodle.getEmail(), "selectedTo",Boolean.toString(jsonMoodle.isSelectedTo()));
			
			//
			
		}

	
	}

	private User jsonMoodToPersist(JsonMoodle jsonMoodle) {
		// TODO Auto-generated method stub
		User user = new User();
		City city = new City();
		Profile profile = new Profile();
		user.setCity(city);
		user.setCpf(jsonMoodle.getCpf()); 
		user.setdType(jsonMoodle.getDtype());
		user.setName(jsonMoodle.getName());
		user.setLastname(jsonMoodle.getLastname());
		user.setEmail(jsonMoodle.getEmail());String url = "localhost";
		user.setHasChangedDefaultPassword(false);
		user.setPassword(null);
		user.setPhoneNumber(jsonMoodle.getPhone());
		user.setProfile(profile);
		
		
		return user;
	}
	
	private JsonMoodle retornaUM(ArrayList<JsonMoodle>usuarios,String chave) {
		// TODO Auto-generated method stub
		JsonMoodle jon = new JsonMoodle();
			for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
				JsonMoodle jsonMoodle = (JsonMoodle) iterator.next();
				if (jsonMoodle.getEmail().equals(chave)) {
					jon = jsonMoodle;
					System.out.println("Gezonel se disfarça de "+jon.getName());
					return jon;
				}
				
			}
			return jon;
		
	}
	
	

}
