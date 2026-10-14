package br.unitins.tp1.service;

import br.unitins.tp1.model.LivroDigital;

@FunctionalInterface
public interface GeradorLinkDownloadTemporario {

    String gerarLinkDownloadTemporario(LivroDigital livro);
}
