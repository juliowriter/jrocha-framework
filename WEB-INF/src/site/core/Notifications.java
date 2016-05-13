package site.core;

public class Notifications {
/*
	public int notifica() {
		//Notificações para usuários que participam de promoções
		String message_template = "Ol&aacute; _name_,<br /><br />Voc&ecirc; est&aacute; participando da promo&ccedil;&atilde;o _promo_.<br /><br />";
		message_template += "Sua posi&ccedil;&atilde;o no ranking &eacute;: _position_<br />Veja abaixo os dez primeiros colocados at&eacute; agora:<br />";
		message_template += "_ranking_<br />";
		message_template += "Para ganhar mais pontos e subir no ranking rapidamente, indique seus amigos para participar da promo&ccedil;&atilde;o.<br/><br/>";
		message_template += "Envie o seu link personalizado para seus amigos:<br /> _link_<br /><br />";
		message_template += "A cada novo amigo que jogar o desafio atrav&eacute;s do seu link, voc&ecirc; ganha mais 10 pontos!<br /><br />";
		message_template += "Boa sorte!<br /><br />";
		message_template += "Equipe Pixboard<br />www.pixboard.com<br />Fotos e Divers&atilde;o";
		//Selecionar promoções
		Iterator<Picx> it = new Picx().getPromos("br", 1, 100).iterator();
		int enviados = 0;
		while(it.hasNext())
		{
			Picx pc = it.next();
			Iterator<GameRanking> itr = new Game().getRankingPoint(pc.getPic_id(), pc.getPic_promotional(), 0, 10).iterator();
			String rank = "";
			int count = 1;
			while(itr.hasNext())
			{
				GameRanking gr = itr.next();
				rank += ((count++) + " - " + gr.getPrf_nickname() + " -> " + gr.getGam_points() + " pontos <br/>");
			}
			Iterator<Game> itg = new Game().getGames(pc.getPic_id()).iterator();
			while(itg.hasNext())
			{
				String message_body = message_template;
				Game gm = itg.next();
				Profile prf = new Profile().getProfile(gm.getPrf_id());
				if(prf!=null)
				{
					message_body = message_body.replace("_name_", prf.getPrf_firstname());
					message_body = message_body.replace("_promo_", pc.getPic_name());
					message_body = message_body.replace("_position_", (gm.getPosition(pc.getPic_id(), gm.getGam_id(), gm.getGam_points()) + 1)+ "");
					message_body = message_body.replace("_ranking_", rank);
					if(gm.getGam_id() == 2)
					{
						message_body = message_body.replace("_link_", "http://www.pixboard.com/playpz?pc=" + pc.getPic_token() + "&rf=" + prf.getPrf_id());
					} else {
						message_body = message_body.replace("_link_", "http://www.pixboard.com/play?pc=" + pc.getPic_token() + "&rf=" + prf.getPrf_id());
					}
					Mailtogo mtg = new Mailtogo();
					mtg.setMtg_address(prf.getPrf_email());
					mtg.setMtg_from("noreply@pixboard.com.br");
					mtg.setMtg_status("Q");
					mtg.setMtg_subject("Indique seus amigos e ganhe mais pontos");
					mtg.setMtg_text(message_body);
					mtg.insert();
					enviados++;
				}
			}
		}
		return enviados;
	}
*/
}
