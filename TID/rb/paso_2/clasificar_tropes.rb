
def clasificar_tropes(clases, valor_defecto)
  hash = Hash.new(valor_defecto)
  clases.each do |clase|
    clase.tropes.each do |trope|
      if hash[trope] != valor_defecto
        puts "Se intento #{trope} => #{clase.nombre}, cuando ya => #{hash[trope]}"
      else
        hash[trope] = clase.nombre
      end
    end
  end
  return hash
end
