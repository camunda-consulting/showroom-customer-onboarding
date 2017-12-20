<%@ page pageEncoding="UTF-8" %>
            <!-- Main Header Start -->
            <div class="main-header">
               <div class="container">
                  <!-- TopNav Start -->
                  <div class="topnav navbar-header">
                     <a class="navbar-toggle down-button" data-toggle="collapse" data-target=".slidedown">
                     <i class="fa fa-angle-down icon-current"></i>
                     </a> 
                  </div>
                  <!-- TopNav End -->
                  <!-- Logo Start -->
                  <div class="logo pull-left">
                     <h1>
                        <a href="index.jsp?lang=${param.lang}">
                        <img src="img/Camundanzia_Logo_Web_w.png" alt="pixma" height="60">
                        </a>
                     </h1>
                  </div>
                  <!-- Logo End -->
                  <!-- Mobile Menu Start -->
                  <div class="mobile navbar-header">
                     <a class="navbar-toggle" data-toggle="collapse" href=".navbar-collapse">
                     <i class="fa fa-bars fa-2x"></i>
                     </a> 
                  </div>
                  <!-- Mobile Menu End -->
                  <!-- Menu Start -->
                  <nav class="collapse navbar-collapse menu">
                     <ul class="nav navbar-nav sf-menu">
                        <li>
                           <a <%= request.getRequestURI().endsWith("index.jsp") ? "id=\"current\"" : "" %> href="index.jsp?lang=${param.lang}">
                           Home
                           </a>
                        </li>
                        <li>
                           <a <%= request.getRequestURI().endsWith("application.jsp") ? "id=\"current\"" : "" %> href="application.jsp?lang=${param.lang}" >
                           ${param.lang == 'de' ? 'Antrag stellen' : 'Apply for Policy'} 
                           </a>
                        </li>
                        <li>
                           <a href="#" >
			   ${param.lang == 'de' ? 'Schaden melden' : 'Create Claim'}
                           </a>
                        </li>
                       <li>
                           <a <%= request.getRequestURI().endsWith("documents.jsp") ? "id=\"current\"" : "" %> href="documents.jsp?lang=${param.lang}">
				   ${param.lang == 'de' ? 'Unterlagen einreichen' : 'Hand in Documents'}
                           </a>
                        </li>                        
                        <li>
                           <a href="#" >
				   ${param.lang == 'de' ? 'Über uns' : 'About us'}
                           </a>
                        </li>

                     </ul>
                  </nav>
                  <!-- Menu End --> 
               </div>
            </div>
            <!-- Main Header End -->
         </header>
         <!-- Header End -->  
