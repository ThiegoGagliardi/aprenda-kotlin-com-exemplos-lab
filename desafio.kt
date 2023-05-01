// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome : String)
{
    private var totalExperiencia : Int = 0
    
    private val conteudosAEstudar = mutableListOf<ConteudoEducacional>()
    
    private val conteudosConcluidos = mutableListOf<ConteudoEducacional>()
    
    fun adiquirirExperiencia(experienciaAdiquirida: Int)
    {
        this.totalExperiencia += experienciaAdiquirida;
    }
    
    fun efetuarMatriculaConteudo(conteudos : List<ConteudoEducacional>)
    {        
        this.conteudosAEstudar.addAll(conteudos);        
    }
    
    fun finalizarConteudoMatriculado()
    {
        val conteudo = conteudosAEstudar.elementAt(0)
        conteudosConcluidos.add(conteudo)
        this.adiquirirExperiencia(conteudo.quantidadeExperiencia)
        conteudosAEstudar.remove(conteudo)
    }
    
    fun verificarTotalExperiencia() : Int
    {       
        return this.totalExperiencia
    } 
    
    fun verificarConteudosAEstuda() : List<ConteudoEducacional>
    {
        val conteudo : List<ConteudoEducacional> =  conteudosAEstudar.toList();
        return conteudo;
    }
    
    fun verificarConteudosConcluidos() : List<ConteudoEducacional>
    {   
        val conteudo : List<ConteudoEducacional> = conteudosConcluidos.toList();
        return conteudo;
    }    
    
    override fun toString() : String
    {
        return "nome= $nome, experiencia = $totalExperiencia";
    }
}

data class ConteudoEducacional(val nome: String, val duracao: Int, val nivel : Nivel, val quantidadeExperiencia: Int)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    private var inscritos = mutableSetOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        
        inscritos.add(usuario)
        usuario.efetuarMatriculaConteudo(conteudos);
    }
    
    fun quantidadeHorasFormacao(): Int
    {        
        var totalHoras : Int = 0
        
        conteudos.forEach{it -> totalHoras += it.duracao} 
        
        return totalHoras
    }
    
    fun verificarInscritos() : List<String>
    {   
        val inscritosLista : List<Usuario> = inscritos.toList();
        // println(inscritosLista)
        return inscritosLista.map{ it.nome}
    }     

 }

fun main() {
    
    // -- Teste Criar usuário
    val jose   = Usuario("Jose")
    val maria  = Usuario("Maria")
    
    println("Criado usuario = $jose");
    println("Criado usuaria = $maria");
    println("");
    
    // -- Teste Criar conteudo
    val algoritmo = ConteudoEducacional("Algoritmo",  5, Nivel.INTERMEDIARIO, 25)
    val java      = ConteudoEducacional("Java",      10, Nivel.INTERMEDIARIO, 100)
    val kotlin    = ConteudoEducacional("Kotlin",    20, Nivel.DIFICIL,       200)
    
    println("Criado conteudo = $algoritmo")
    println("Criado conteudo = $java")
    println("Criado conteudo = $kotlin")
    println("");
    
    // -- Teste Criar formacao
    var conteudosFormacao = mutableListOf<ConteudoEducacional>() 
    conteudosFormacao     = mutableListOf(algoritmo, java, kotlin)
    val formacao          = Formacao("TQI - Java e Kotlin", conteudosFormacao)
    
    println("Criada formacao = $formacao");
    println("Quantidade horas formacao = "+ formacao.quantidadeHorasFormacao());
    println("");
    
    // -- Teste matricular
    formacao.matricular(jose)
    formacao.matricular(maria)
    println("Efetuada inscricao dos usuarios = " + formacao.verificarInscritos())
    println("");
    
    // -- Teste avança nos estudos
     
    jose.finalizarConteudoMatriculado();
    println("Usuario $jose finalizou um conteudo");
    println("Conteudo concluido = "+ jose.verificarConteudosConcluidos())
    println("Conteudo pendente = " + jose.verificarConteudosAEstuda())
    println("Experiencia total = " + jose.verificarTotalExperiencia())
    
    println("");
    maria.finalizarConteudoMatriculado();
    maria.finalizarConteudoMatriculado();
    println("Usuario $maria finalizou conteudo");
    println("Conteudo concluido = "+ maria.verificarConteudosConcluidos())
    println("Conteudo pendente = " + maria.verificarConteudosAEstuda())
    println("Experiencia total = " + maria.verificarTotalExperiencia()) 
}