package com.Hique2605.Course.config;

import java.time.Instant;
import java.util.Arrays;

import com.Hique2605.Course.entities.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Hique2605.Course.entities.*;
import com.Hique2605.Course.entities.enums.OrderStatus;
import com.Hique2605.Course.repositories.*;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private VinhoRepository vinhoRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private RepresentanteRepository representanteRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public void run(String... args) throws Exception {

		String imgUrl = "https://images.tcdn.com.br/img/img_prod/1022248/vinho_frances_tinto_chateau_de_sales_2014_garrafa_750ml_741_1_ea70f195ee99027a995afab072565ae3.jpg";

		// Vinhos (5 com a mesma imagem)
		Vinho v1 = new Vinho(null, "Vinho Merlot", "2020", "Tinto", "Merlot", "13%", "750ml",
				"Notas frutadas", "Carnes vermelhas", 89.90, imgUrl, 50.0, true);
		Vinho v2 = new Vinho(null, "Vinho Chardonnay", "2021", "Branco", "Chardonnay", "12%", "750ml",
				"Aroma floral", "Peixes", 69.90, imgUrl, 30.0, true);
		Vinho v3 = new Vinho(null, "Vinho Rosé", "2022", "Rosé", "Pinot Noir", "11.5%", "750ml",
				"Refrescante e leve", "Massas", 59.90, imgUrl, 40.0, true);
		Vinho v4 = new Vinho(null, "Vinho Cabernet", "2019", "Tinto", "Cabernet Sauvignon", "13.5%", "750ml",
				"Encorpado e intenso", "Queijos fortes", 99.90, imgUrl, 25.0, true);
		Vinho v5 = new Vinho(null, "Vinho Syrah", "2020", "Tinto", "Syrah", "14%", "750ml",
				"Notas de especiarias", "Churrasco", 79.90, imgUrl, 35.0, true);

		vinhoRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5));

		// Admin com e-mail e senha específicos
		Admin adm01 = new Admin(null, UserType.ADMIN, "Administrador", "admin@gmail.com", "99999999", "admin");
		adminRepository.saveAll(Arrays.asList(adm01));

		// Representantes (ajustado o 3º)
		Representante r1 = new Representante(null, UserType.REPRESENTANTE, "Carlos Silva", "07206101925", "carlos@rep.com", "11999999999", "senha123", 5000.0);
		Representante r2 = new Representante(null, UserType.REPRESENTANTE, "Fernanda Lima", "07206101926", "fernanda@rep.com", "11988888888", "senha456", 7000.0);
		Representante r3 = new Representante(null, UserType.REPRESENTANTE, "João Batista", "07206101927", "joao@rep.com", "11977777777", "senha789", 6500.0);
		representanteRepository.saveAll(Arrays.asList(r1, r2, r3));

		// Usuários (ajustado o 3º)
		User u1 = new User(null, UserType.USER, "Maria Brown", "maria@gmail.com", "988888888", "Criciuma", "RUA A", "123456");
		User u2 = new User(null, UserType.USER, "Alex Green", "alex@gmail.com", "977777777", "Meleiro", "RUA B", "123456");
		User u3 = new User(null, UserType.USER, "Joãozinho", "joaozinho@gmail.com", "966666666", "Torres", "RUA C", "123456");
		userRepository.saveAll(Arrays.asList(u1, u2, u3));

		// Pedidos com representante vinculado
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1, r1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2, r2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1, r1);
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		// Itens do pedido
		OrderItem oi1 = new OrderItem(o1, v1, 2, v1.getPrecoUnitario());
		OrderItem oi2 = new OrderItem(o1, v3, 1, v3.getPrecoUnitario());
		OrderItem oi3 = new OrderItem(o2, v3, 2, v3.getPrecoUnitario());
		OrderItem oi4 = new OrderItem(o3, v5, 2, v5.getPrecoUnitario());
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

		// Pagamento do pedido
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		orderRepository.save(o1); // necessário pois o cascade já está configurado
	}
}
