-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 13-Fev-2017 às 19:50
-- Versão do servidor: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carona`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `avaliacao`
--

CREATE TABLE `avaliacao` (
  `id` bigint(20) NOT NULL,
  `idUsuario` bigint(20) NOT NULL,
  `estrelas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `carona`
--

CREATE TABLE `carona` (
  `id` bigint(20) NOT NULL,
  `idVeiculo` bigint(11) NOT NULL,
  `data` varchar(20) NOT NULL,
  `horaSaida` varchar(20) NOT NULL,
  `vagas` int(11) NOT NULL,
  `logradouroOrigem` bigint(20) NOT NULL,
  `logradouroDestino` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `carona`
--

INSERT INTO `carona` (`id`, `idVeiculo`, `data`, `horaSaida`, `vagas`, `logradouroOrigem`, `logradouroDestino`) VALUES
(1, 2, '2017-02-13', '14:10', 4, 1, 3),
(2, 2, '2017-02-13', '16:18', 4, 1, 3),
(3, 2, '2017-02-15', '18:09', 2, 1, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `caronausuario`
--

CREATE TABLE `caronausuario` (
  `idCarona` bigint(20) NOT NULL,
  `idUsuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupo`
--

CREATE TABLE `grupo` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `regras` text NOT NULL,
  `limiteMinimo` int(11) NOT NULL,
  `estaAtivo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `grupo`
--

INSERT INTO `grupo` (`id`, `nome`, `descricao`, `regras`, `limiteMinimo`, `estaAtivo`) VALUES
(6, 'Grupo 1', 'sei sei sei', ' Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse mattis mi magna, id ornare turpis vehicula vel. Vivamus eget enim suscipit, pretium purus nec, vulputate risus. Nam vehicula id sem non imperdiet. Nam vitae blandit urna. Suspendisse sodales mauris vel elementum cursus. Fusce mattis et tellus sit amet commodo. Sed lorem quam, cursus ut pellentesque at, feugiat at lacus. In maximus ante et quam sollicitudin convallis. Nulla sit amet urna et felis ullamcorper lobortis et vel justo. Nam dolor diam, accumsan vel neque quis, eleifend sodales orci. Duis sodales lorem eget augue accumsan, sit amet varius neque pulvinar. Donec lectus libero, scelerisque id pharetra eget, lacinia eu enim. ', 4, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `logradouro`
--

CREATE TABLE `logradouro` (
  `id` bigint(20) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `distrito` varchar(30) NOT NULL,
  `endereco` varchar(60) NOT NULL,
  `numero` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `logradouro`
--

INSERT INTO `logradouro` (`id`, `cep`, `estado`, `cidade`, `distrito`, `endereco`, `numero`) VALUES
(1, '26011352', 'RJ', 'Nova Iguaçu', 'Engenho Pequeno', 'Rua Primeiro de Maio', '223'),
(2, '26011400', 'RJ', 'Nova Iguaçu', 'Engenho Pequeno', 'Rua Maria da Glória', '141'),
(3, '26030420', 'RJ', 'Nova Iguaçu', 'Posse', 'Rua Paranaguá', '81');

-- --------------------------------------------------------

--
-- Estrutura da tabela `motorista`
--

CREATE TABLE `motorista` (
  `idUsuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `motorista`
--

INSERT INTO `motorista` (`idUsuario`) VALUES
(1),
(8),
(9),
(11);

-- --------------------------------------------------------

--
-- Estrutura da tabela `parada`
--

CREATE TABLE `parada` (
  `id` bigint(20) NOT NULL,
  `idCarona` bigint(20) NOT NULL,
  `idLogradouro` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `passageiros`
--

CREATE TABLE `passageiros` (
  `idCarona` bigint(20) NOT NULL,
  `idUsuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `passageiros`
--

INSERT INTO `passageiros` (`idCarona`, `idUsuario`) VALUES
(1, 5),
(1, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL DEFAULT '1234',
  `senha` varchar(20) NOT NULL,
  `telefone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `telefone`) VALUES
(1, 'Renan', 'renan@renan.com', '1234', '37732315'),
(5, 'Douglas', 'douglas@ufrrj.br', '1234', '3888888888'),
(6, 'Bool', 'renan_nd@yahoo.com.br', '1234', '55555555'),
(7, 'Artur', 'artur@ufrrj.br', '1234', '111111111'),
(8, 'Agostinho', 'agosto@setembro', '1234', '444444444'),
(9, 'Bool', 'bool@saur.com', '1234', '3333333333'),
(10, 'naomotorista', 'naomotorista@naomotorista', '1234', '123456567'),
(11, 'Motorista', 'motorista@motorista', '1234', '12345676'),
(12, 'default', 'default@default', '1234', '11111111');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuariogrupo`
--

CREATE TABLE `usuariogrupo` (
  `idUsuario` bigint(20) NOT NULL,
  `idGrupo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuariogrupo`
--

INSERT INTO `usuariogrupo` (`idUsuario`, `idGrupo`) VALUES
(1, 6),
(5, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `id` bigint(20) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `placa` varchar(10) NOT NULL,
  `cor` varchar(25) NOT NULL,
  `idMotorista` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `veiculo`
--

INSERT INTO `veiculo` (`id`, `modelo`, `placa`, `cor`, `idMotorista`) VALUES
(1, 'BMW', 'abc-0123', 'Vermelha', 9),
(2, 'Mercedes', 'acb-0258', 'Prata', 1),
(3, 'Super Carrinho', 'spr1234', 'Preto', 11);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `avaliacao`
--
ALTER TABLE `avaliacao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `carona`
--
ALTER TABLE `carona`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `caronausuario`
--
ALTER TABLE `caronausuario`
  ADD PRIMARY KEY (`idCarona`,`idUsuario`);

--
-- Indexes for table `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `logradouro`
--
ALTER TABLE `logradouro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `motorista`
--
ALTER TABLE `motorista`
  ADD PRIMARY KEY (`idUsuario`);

--
-- Indexes for table `parada`
--
ALTER TABLE `parada`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `passageiros`
--
ALTER TABLE `passageiros`
  ADD PRIMARY KEY (`idCarona`,`idUsuario`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuariogrupo`
--
ALTER TABLE `usuariogrupo`
  ADD PRIMARY KEY (`idUsuario`,`idGrupo`);

--
-- Indexes for table `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `avaliacao`
--
ALTER TABLE `avaliacao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `carona`
--
ALTER TABLE `carona`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `grupo`
--
ALTER TABLE `grupo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `logradouro`
--
ALTER TABLE `logradouro`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `motorista`
--
ALTER TABLE `motorista`
  MODIFY `idUsuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `parada`
--
ALTER TABLE `parada`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
